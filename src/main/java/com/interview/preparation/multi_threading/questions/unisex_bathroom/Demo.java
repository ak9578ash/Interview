package com.interview.preparation.multi_threading.questions.unisex_bathroom;

public class Demo {
  public static void main(String[] args) {
    Bathroom bathroom = new Bathroom(3);

    Thread male1 = Thread.ofPlatform().name("male-1").unstarted(
        () -> {
          try {
            bathroom.maleEnter();
            Thread.sleep(3000);
            bathroom.maleExit();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
    );

    Thread male2 = Thread.ofPlatform().name("male-2").unstarted(
        () -> {
          try {
            bathroom.maleEnter();
            Thread.sleep(3000);
            bathroom.maleExit();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
    );

    Thread male3 = Thread.ofPlatform().name("male-3").unstarted(
        () -> {
          try {
            bathroom.maleEnter();
            Thread.sleep(3000);
            bathroom.maleExit();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
    );

    Thread male4 = Thread.ofPlatform().name("male-4").unstarted(
        () -> {
          try {
            bathroom.maleEnter();
            Thread.sleep(3000);
            bathroom.maleExit();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
    );

    Thread female1 = Thread.ofPlatform().name("female-1").unstarted(
        () -> {
          try {
            bathroom.femaleEnter();
            Thread.sleep(3000);
            bathroom.femaleExit();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
    );

//    Thread female2 = Thread.ofPlatform().name("female-2").unstarted(
//        () -> {
//          try {
//            bathroom.femaleEnter();
//            Thread.sleep(6000);
//            bathroom.femaleExit();
//          } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//          }
//        }
//    );
//
//    Thread female3 = Thread.ofPlatform().name("female-3").unstarted(
//        () -> {
//          try {
//            bathroom.femaleEnter();
//            Thread.sleep(6000);
//            bathroom.femaleExit();
//          } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//          }
//        }
//    );

//    male1.start();
//    male2.start();
//    male3.start();
//    try {
//      Thread.sleep(2000);
//    } catch (InterruptedException e) {
//      Thread.currentThread().interrupt();
//    }
//    female1.start();
//    female2.start();
//    female3.start();
//
//    male4.start();

    female1.start();
    male1.start();
    male2.start();
    male3.start();
    male4.start();

    try {
      female1.join();
      male1.join();
      male2.join();
      male3.join();
      male4.join();
    }catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
