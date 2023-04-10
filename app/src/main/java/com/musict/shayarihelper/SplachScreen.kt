package com.musict.shayarihelper

import android.R
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.musict.shayarihelper.databinding.ActivitySplachScreenBinding


class SplachScreen : AppCompatActivity() {

     lateinit var binding : ActivitySplachScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplachScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        //Normal Handler is deprecated , so we have to change the code little bit

        // Handler().postDelayed({
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.


// Custom animation speed or duration.

// Custom animation speed or duration.

// Custom animation speed or duration.
//        val animator = ValueAnimator.ofFloat(0f, 1f)
//        animator
//            .addUpdateListener { animation: ValueAnimator ->
//                animationView
//                    .setProgress(
//                        animation
//                            .animatedValue
//                    )
//            }
//        animator.start()
//
//        // Declaring the animation view
//        // Declaring the animation view
//        val animationView = findViewById<LottieAnimationView>(R.id.ani)
//        animationView
//            .addAnimatorUpdateListener { animation: ValueAnimator? -> }
//        animationView
//            .playAnimation()
//
//        if (animationView.isAnimating) {
//            // Do something.
//        }






    }

}