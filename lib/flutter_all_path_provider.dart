import 'dart:async';
import 'dart:math';

import 'package:flutter/services.dart';

class FlutterAllPathProvider {
  static const MethodChannel _channel =
      MethodChannel('flutter_all_path_provider');

  static Future<List<StorageInfo>> getStorageInfo() async {
    List reply = await _channel.invokeMethod('getExtStorageData', []);
    List<StorageInfo> storageInfos = reply
        .map((storageInfoMap) => StorageInfo.fromJson(storageInfoMap))
        .toList();

    return storageInfos;
  }
}

class StorageInfo {
  final String appFilesDir;
  final int availableBytes;
  int get availableGB => availableBytes ~/ pow(2, 30);
  int get availableMB => availableBytes ~/ pow(2, 20);

  String get rootDir => appFilesDir
      .split("/")
      .sublist(0, appFilesDir.split("/").length - 4)
      .join("/");

  StorageInfo(this.appFilesDir, this.availableBytes);

  factory StorageInfo.fromJson(Map json) {
    return StorageInfo(json["path"], json["availableBytes"]);
  }
//get root with: reply[0]["path"].split("/").sublist(0,reply[0]["path"].split("/").length-4).join("/");
}
