package com.example.xinwen.util

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import com.example.xinwen.base.BaseApplication
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


object RecordingUtil {
    private var audioRecord: AudioRecord? = null
    private var isRecording = false

    private var recordingAudioThread: Thread? = null

    // 采样率，现在能够保证在所有设备上使用的采样率是44100Hz, 但是其他的采样率（22050, 16000, 11025）在一些设备上也可以使用。
    private const val SAMPLE_RATE_INHZ = 44100

    // 声道数。CHANNEL_IN_MONO and CHANNEL_IN_STEREO. 其中CHANNEL_IN_MONO是可以保证在所有设备能够使用的。
    private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_STEREO

    // 返回的音频数据的格式。 ENCODING_PCM_8BIT, ENCODING_PCM_16BIT, and ENCODING_PCM_FLOAT.
    private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
    private  var audioCacheFilePath: String = ""
    /**
     * 开始录音，返回临时缓存文件（.pcm）的文件路径
     */
    fun startRecordAudio(): String {
        audioCacheFilePath = BaseApplication.getContext()
            .getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.absolutePath + "/" + "jerboa_audio_cache.pcm"
        try {
            // 获取最小录音缓存大小，
            val minBufferSize =
                AudioRecord.getMinBufferSize(SAMPLE_RATE_INHZ, CHANNEL_CONFIG, AUDIO_FORMAT)
            audioRecord = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE_INHZ,
                CHANNEL_CONFIG,
                AUDIO_FORMAT,
                minBufferSize
            )
            // 开始录音
            isRecording = true
            audioRecord?.startRecording()

            // 创建数据流，将缓存导入数据流
            recordingAudioThread = Thread(Runnable {
                val file = File(audioCacheFilePath)
                Log.i(TAG, "audio cache pcm file path:$audioCacheFilePath")

                /*
                 *  以防万一，看一下这个文件是不是存在，如果存在的话，先删除掉
                 */
                if (file.exists()) {
                    file.delete()
                }
                try {
                    file.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                var fos: FileOutputStream? = null
                try {
                    fos = FileOutputStream(file)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                    Log.e(TAG, "临时缓存文件未找到")
                }
                if (fos == null) {
                    return@Runnable
                }
                val data = ByteArray(minBufferSize)
                var read: Int?
                if (fos != null) {
                    while (isRecording && !recordingAudioThread!!.isInterrupted) {
                        read = audioRecord?.read(data, 0, minBufferSize)
                        if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                            try {
                                fos.write(data)
                                Log.i("audioRecordTest", "写录音数据->$read")
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
                try {
                    // 关闭数据流
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            })
            recordingAudioThread?.start()
        } catch (e: IllegalStateException) {
            Log.w(TAG, "需要获取录音权限！")
        } catch (e: SecurityException) {
            Log.w(TAG, "需要获取录音权限！")
        }
        return audioCacheFilePath
    }

    /**
     * 停止录音
     */
    fun stopRecordAudio() {
        try {
            isRecording = false
            if (audioRecord != null) {
                audioRecord!!.stop()
                audioRecord!!.release()
                audioRecord = null
                recordingAudioThread!!.interrupt()
                recordingAudioThread = null
            }
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.w(TAG, it) }
        }
    }

    fun getWav() {
        //wav文件的路径放在系统的音频目录下
        val wavFilePath: String = BaseApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_PODCASTS)?.path + "/wav_" + System.currentTimeMillis() + ".wav"

        val ptwUtil = PcmToWavUtil()
        ptwUtil.pcmToWav("Your ppm file path",wavFilePath,true)
    }

    const val TAG = "RecordingUtil"

}