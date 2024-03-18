<h2> Как проверять проект </h2>

* В application.properties нужно указать параметры для скачивания файлов:

```
downloader.max-threads=8 - количество потоков для обработки \
downloader.bytes-per-second=500000 - ограничение скорости загрузки в битах\
downloader.links-file-path=links.txt - путь к файлу с ссылками на ресурсы\
downloader.download-directory-path=downloaded_files  - директория, куда будут сохранятся ресурсы 
```

* В пакете config находится класс с парамерами из конфигурации. В пакете repository выделены методы для работы с файлами.
В пакете service находится метод, реализующий логику скачивания файлов в нескольких потоках.

<h3> Логи </h3>

```
2024-03-18T21:33:19.376+03:00  INFO 6052 --- [Lab17] [           main] com.example.demo.DemoApplication         : Starting DemoApplication using Java 21.0.2 with PID 6052 (C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\target\classes started by astaf in C:\Users\astaf\IdeaProjects\JavaTasks\Lab17)
2024-03-18T21:33:19.378+03:00  INFO 6052 --- [Lab17] [           main] com.example.demo.DemoApplication         : No active profile set, falling back to 1 default profile: "default"
2024-03-18T21:33:20.091+03:00  INFO 6052 --- [Lab17] [           main] com.example.demo.DemoApplication         : Started DemoApplication in 1.289 seconds (process running for 2.166)
2024-03-18T21:33:20.760+03:00  INFO 6052 --- [Lab17] [pool-2-thread-5] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\unsplash_52424d6a9e278_1.JPG
2024-03-18T21:33:20.802+03:00  INFO 6052 --- [Lab17] [pool-2-thread-4] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1458408990864-27997f8c2984
2024-03-18T21:33:20.858+03:00  INFO 6052 --- [Lab17] [pool-2-thread-3] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1455817626763-72371127d324
2024-03-18T21:33:20.887+03:00  INFO 6052 --- [Lab17] [pool-2-thread-1] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1710493559740-e04b9e89ef92
2024-03-18T21:33:20.928+03:00  INFO 6052 --- [Lab17] [pool-2-thread-2] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1582323883418-fe9edc3d129c
2024-03-18T21:33:21.275+03:00  INFO 6052 --- [Lab17] [pool-2-thread-1] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\meta.csv
2024-03-18T21:33:21.453+03:00  INFO 6052 --- [Lab17] [pool-2-thread-8] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1682686581312-506a8b53190e
2024-03-18T21:33:21.470+03:00  INFO 6052 --- [Lab17] [pool-2-thread-7] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1710438399463-e7b374c2db9e
2024-03-18T21:33:23.194+03:00  INFO 6052 --- [Lab17] [pool-2-thread-5] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1708545158412-ae3d000a2edf
2024-03-18T21:33:23.371+03:00  INFO 6052 --- [Lab17] [pool-2-thread-4] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1682685797168-613fd0cae41d
2024-03-18T21:33:23.371+03:00  INFO 6052 --- [Lab17] [pool-2-thread-6] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1710349749007-749f8e066d2b
2024-03-18T21:33:23.849+03:00  INFO 6052 --- [Lab17] [pool-2-thread-3] c.e.d.repository.DownloaderRepository    : Файл загружен: C:\Users\astaf\IdeaProjects\JavaTasks\Lab17\downloaded_files\photo-1707407947418-fa6d4ca53319
```
