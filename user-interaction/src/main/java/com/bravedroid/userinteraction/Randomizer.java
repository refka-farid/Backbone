package com.bravedroid.userinteraction;

import androidx.annotation.VisibleForTesting;

import java.util.Random;

public interface Randomizer {
    int getRandomIndex(int bound);

    class LoopWithForRandomizerVersionOne implements Randomizer {
        private int oldIndex = -1;

        LoopWithForRandomizerVersionOne() {
        }

        LoopWithForRandomizerVersionOne(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @VisibleForTesting
        void setOldIndex(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @Override
        public int getRandomIndex(int bound) {
            if (bound <= 0) {
                throw new IllegalArgumentException();
            }
            Random random = new Random();
            int newIndex = random.nextInt(bound);
            for (; oldIndex == newIndex; ) {
                newIndex = random.nextInt(bound);
            }
            oldIndex = newIndex;
            return newIndex;
        }
    }

    class LoopWithForRandomizerVersionTwo implements Randomizer {
        private int oldIndex = -1;

        LoopWithForRandomizerVersionTwo() {
        }

        LoopWithForRandomizerVersionTwo(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @VisibleForTesting
        void setOldIndex(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @Override
        public int getRandomIndex(int bound) {
            if (bound <= 0) {
                throw new IllegalArgumentException();
            }
            Random random = new Random();
            int newIndex = random.nextInt(bound);
            for (; oldIndex == newIndex; newIndex = random.nextInt(bound)) {
            }
            oldIndex = newIndex;
            return newIndex;
        }
    }

    class LoopWithWhileRandomizerVersionOne implements Randomizer {
        private int oldIndex = -1;

        LoopWithWhileRandomizerVersionOne() {
        }

        LoopWithWhileRandomizerVersionOne(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @VisibleForTesting
        void setOldIndex(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @Override
        public int getRandomIndex(int bound) {
            if (bound <= 0) {
                throw new IllegalArgumentException();
            }
            Random random = new Random();
            int newIndex = random.nextInt(bound);
            while (oldIndex == newIndex) {
                newIndex = random.nextInt(bound);
            }
            oldIndex = newIndex;
            return newIndex;
        }
    }

    class LoopWithDoWhileRandomizer implements Randomizer {
        private int oldIndex = -1;

        LoopWithDoWhileRandomizer() {
        }

        LoopWithDoWhileRandomizer(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @VisibleForTesting
        void setOldIndex(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @Override
        public int getRandomIndex(int bound) {
            if (bound <= 0) {
                throw new IllegalArgumentException();
            }
            Random random = new Random();
            int newIndex = random.nextInt(bound);
            do {
                newIndex = random.nextInt(bound);
            } while (oldIndex == newIndex);
            oldIndex = newIndex;
            return newIndex;
        }
    }

    class LoopWithWileRandomizerVersionTwo implements Randomizer {
        private int oldIndex = -1;

        LoopWithWileRandomizerVersionTwo() {
        }

        LoopWithWileRandomizerVersionTwo(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @VisibleForTesting
        void setOldIndex(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        @Override
        public int getRandomIndex(int bound) {
            if (bound <= 0) {
                throw new IllegalArgumentException();
            }
            Random random = new Random();
            int newIndex = random.nextInt(bound);
            while (true) {
                if (newIndex != oldIndex) {
                    break;
                }
                newIndex = random.nextInt(bound);
            }
            oldIndex = newIndex;
            return newIndex;
        }
    }
}
