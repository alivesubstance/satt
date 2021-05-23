Recorder

Приложения для записи пользовательских шагов в сценарий с возможностью последующего их повторения.

Пререквизиты
1) Скалирование экрана должно быть выставлено в 100%(как по-умолчанию)
2) Для работы приложения требуется установка Java SE 11, которую можно скачать
с официального сайта Oracle https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html
Системная переменная PATH должена содержать путь до bin директории из поставке JRE.


Структура поставки:
    - config, папка с конфигурационными файлами
        - application.properties, файл с настройками приложения
        - log42j.xml, файл с настройками логирования
    - lib, Java Archive приложения
    - logs, файлы логирования


Настройка
Для сохранений сценария приложение использует сторонний REST API сервис и отправляет объект сценария
на endpoint, выставленный этим сервисом, использую HTTP POST запрос.
Путь до REST API сервиса настраивается в файле config/application.properties:
scenarioUrl=http://localhost:3000/scenario


Приложение работает на MAC и Windows платформах. Поддерживаются следующие события:
    - нажатие клавиш на клавиатуре(в том числе комбинаций)
    - нажатие кнопок мышки
    - скроллинг колесиком мышки(см. разъяснение ниже)
При записи скроллинга для начала надо кликнуть на ту область, которую будете скролить.
Обычно кликать не надо, а только подвести мышь в нужную область, но для корректной записи
сценария надо подвести мышь и кликнуть левой кнопкой.


Приложения запускается в консольном режиме с использование(в поставке для каждой платформы будет только один из двух файлов):
    - recorder.command, для MAC
    - recorder.bat, для WIN


Для управления сценарием используются комбинации клавиш:
    |      Старт записи      |     Остановка записи
MAC | command+ctrl+shift+F9  | command+ctrl+shift+F10
WIN | alt+ctrl+shift+F9      | alt+ctrl+shift+F10


Типичные сообщения в логах
    - "Started Recorder in 0.878 seconds (JVM running for 6.56)", приложение запущено и готово к работе
    - "Start new scenario", начало записи сценария
    - "Finish scenario", окончание записи сценария


Особенности записи сценария
1) В приложении не поддерживается автоматическая смена раскладки клавиатуры. То есть запись и воспроизведение
    сценария всегда нужно начинать с одинаковой раскладки.
2) Не работает запись нажатий кнопок:
    MAC:
        а) = на дополнительной клавиатуре
        б) - на дополнительной клавиатуре
        в) + на дополнительной клавиатуре
        г) caps lock
    WIN:
        а) - на дополнительной клавиатуре
        б) + на дополнительной клавиатуре


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

3) Ошибка означает что приложение не удалось отправить сценарий для сохранения на сторонний REST API сервис.
    [ERROR] 2021-05-23 16:40:13.236 [JNativeHook Dispatch Thread] ScenarioService -
        Failed to save scenario fc4a5c50-21b0-42c7-aeca-477fe3f00549 but it will be stopped
        java.lang.RuntimeException: com.mashape.unirest.http.exceptions.UnirestException:
        org.apache.http.conn.HttpHostConnectException:
        Connect to localhost:31000 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed:
        Connection refused (Connection refused)

    При этом сам сценарий будет записан в лог в сообщение:
        Failed to save scenario
