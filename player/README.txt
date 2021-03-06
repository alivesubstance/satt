Player


Приложения для проигрывания пользовательского сценария, записанного в приложении Recorder.


Для работы приложения требуется установка Java SE 11, которую можно скачать
с официального сайта Oracle https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html
Системная переменная PATH должна содержать путь до bin директории из поставке JRE.

Описание
Приложение запускает сервлет контейнер на порту, который считывается из свойства server.port=31000
из application.properties файла. Так же конфигурирует HTTP POST /scenario endpoint, который принимает
сценарий и начинает его воспроизведение по шагам.


Структура поставки:
    - config, папка с конфигурационными файлами
        - application.properties, файл с настройками приложения
        - log42j.xml, файл с настройками логирования
    - lib, Java Archive приложения
    - logs, файлы логирования


Приложения запускается в консольном режиме с использование(в поставке для каждой платформы будет только один из двух файлов):
    - player.command, для MAC
    - player.bat, для WIN


Типичные сообщения в логах
    - "Started Player in 0.933 seconds (JVM running for 6.451)", приложение запущено и готово к работе
    - "Start playing scenario", начало проигрывания сценария
    - "Finish playing scenario", окончание проигрывания сценария


Сборка из исходников
1) Установить Java SE 11. Выставить системную переменную JAVA_HOME=$PATH_TO_JRE_INSTALL_DIR
2) Добавить $JAVA_HOME/bin в $PATH
3) Проверить что java усновлена вызвав в консоли java -version
4) Установить Maven https://maven.apache.org/install.html
5) Проверить что maven установлен через консоль mvn -version
6) Выкачать репозитори с https://github.com/alivesubstance/satt
7) В рутовой директории проекта в консоли выполнить команду mvn clean install
8) По окончанию сборки должна появиться надпись BUILD SUCCESS
9) После удачной сборки появится артефакт player/target/player-0.1-uber.jar
10) Скопировать его в player/build/$PLATFORM/lib
11) Плеер запускается командой player/build/$PLATFORM/player.{command для mac, bat для win}


Диагностика неполадок
1) Расширенный сбор логов. Для этого необходимо в файле config/log4j2.xml изменить уровень логирования c info на trace:
    Было:
        <Loggers>
            <Root level="info" additivity="false">
                <AppenderRef ref="console" />
                <AppenderRef ref="file" />
            </Root>
            <Logger name="satt" level="info" />
        </Loggers>

    Стало:
        <Loggers>
            <Root level="info" additivity="false">
                <AppenderRef ref="console" />
                <AppenderRef ref="file" />
            </Root>
            <Logger name="satt" level="trace" />
        </Loggers>

2) Если приложение отваливается из-за нехватки памяти(сначала работает медленно, а потом вообще перестаёт),
    то в логах будет сообщение:
        java.lang.OutOfMemoryError: Java heap space
        Dumping heap to java_pidXXXX.hprof
    Нужно сохранить файл hprof для дальнейшей диагностики.
    Быстрое решение поднять память настройкой -Xmx128m -> -Xmx256m в файле запуска приложения.