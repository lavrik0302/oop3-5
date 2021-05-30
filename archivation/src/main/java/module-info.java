import by.bsuir.m0rk4.oop.task.fourth.core.service.DataArchivator;
import by.bsuir.m0rk4.oop.task.fourth.archivation.DataArchivatorImpl;

module archivation {
    requires core;

    provides DataArchivator with DataArchivatorImpl;
}