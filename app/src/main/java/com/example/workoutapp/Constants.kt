package com.example.workoutapp

object Constants {

    fun defaultExerciseList() : ArrayList<ExerciseModel>{
        val exerciseList  = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(1, "Jumping Jacks", R.drawable.ic_jumping_jacks, false, false) //based on previous two lines
        val plank = ExerciseModel(2, "Plank", R.drawable.ic_plank, false, false)//based on previous two lines
        val pushUp = ExerciseModel(3, "Push Up", R.drawable.ic_push_up, false, false)//based on previous two lines
        val sidePlank = ExerciseModel(4, "Side Plank", R.drawable.ic_side_plank, false, false)//based on previous two lines
        val squat = ExerciseModel(5, "Squat", R.drawable.ic_squat, false, false)//based on previous two lines
        val tricepDip = ExerciseModel(6, "Triceps Dip", R.drawable.ic_triceps_dip_on_chair, false, false)//based on previous two lines
        val wallSit = ExerciseModel(7, "Wall Sit", R.drawable.ic_wall_sit, false, false)
        val lunge = ExerciseModel(8, "Lunge", R.drawable.ic_lunge, false, false)
        val pushUpAndRotation = ExerciseModel(9, "Push up and Rotation", R.drawable.ic_push_up_and_rotation, false, false)
        val abdominalCrunch = ExerciseModel(10, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, false, false)
        val highKneesRunning = ExerciseModel(11, "High Knees Running", R.drawable.ic_high_knees_running_in_place, false, false)
        val stepUpOntoChair = ExerciseModel(12, "Step-up onto Chair", R.drawable.ic_step_up_onto_chair, false, false)

        exerciseList.add(jumpingJacks)
        exerciseList.add(wallSit)
        exerciseList.add(lunge)
        exerciseList.add(plank)
        exerciseList.add(pushUp)
        exerciseList.add(sidePlank)
        exerciseList.add(squat)
        exerciseList.add(tricepDip)
        exerciseList.add(pushUpAndRotation)
        exerciseList.add(abdominalCrunch)
        exerciseList.add(highKneesRunning)
        exerciseList.add(stepUpOntoChair)


        return exerciseList

    }
}