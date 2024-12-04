package com.interview.preparation.multi_threading.questions.build_molecule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    H2OMachine h2oMachine = new H2OMachine();

    Thread th1 = Thread.ofPlatform().name("HYDROGEN-1")
        .unstarted(
            () -> {
              try {
                h2oMachine.hydrogenAtom();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread th2 = Thread.ofPlatform().name("HYDROGEN-2")
        .unstarted(
            () -> {
              try {
                h2oMachine.hydrogenAtom();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread th3 = Thread.ofPlatform().name("HYDROGEN-3")
        .unstarted(
            () -> {
              try {
                h2oMachine.hydrogenAtom();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread th4 = Thread.ofPlatform().name("OXYGEN-1")
        .unstarted(
            () -> {
              try {
                h2oMachine.oxygenAtom();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread th5 = Thread.ofPlatform().name("OXYGEN-2")
        .unstarted(
            () -> {
              try {
                h2oMachine.oxygenAtom();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    th1.start();
    th4.start();
    th5.start();
//    try {
//      Thread.sleep(1000);
//    } catch (InterruptedException e) {
//      Thread.currentThread().interrupt();
//    }
    th3.start();
    th2.start();


    try {
      th1.join();
      th3.join();
      th2.join();
      th4.join();
      th5.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    log.info("Molecule: {}", h2oMachine.getMolecule());
  }
}
