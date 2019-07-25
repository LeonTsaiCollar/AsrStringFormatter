package com.example.asrstringformatter;


import java.util.Arrays;
import java.util.List;

public class AsrStringFormatter {

    private static String[] strNumHub = {"zero", "one", "two", "three", "four", "five", "six", "eight", "nine"};
    private static String[] strUnitHub = {"hundred", "thousand", "million"};

    //语音识别输出结果
    //汉语和阿拉伯数字混合字符串格式化
    public static String iFlyFormat(String source) {
        String[] splitSrc = source.split(" ");
        if (splitSrc.length > 1) {
            return parseContainSpaceStr(splitSrc);
        } else {
            return parseContinuousStr(source);
        }
    }


    public static String baiduFormat(String source){
        return parseContinuousStr(source);
    }

    //有时候会出现有空格不连续的字符串，一般是英文识别结果
    //One four 3.2
    private static String parseContainSpaceStr(String[] source) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> compareNumList = Arrays.asList(strNumHub);
        List<String> compareUnitList = Arrays.asList(strUnitHub);
        for (String src : source) {
            if (compareNumList.contains(src.toLowerCase())) {
                stringBuilder.append(compareNumList.indexOf(src.toLowerCase()));
            } else if ("point".equals(src)) {
                stringBuilder.append(".");
            } else if (compareUnitList.contains(src)) {
                continue;
            } else {
                stringBuilder.append(src);
            }
        }
        return stringBuilder.toString();
    }


    //如果识别结果准确就是没有空格的连续字符串，一般是中文识别结果
    //1234元3毛4分
    private static String parseContinuousStr(String source) {
        char[] charArr = source.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            char k = charArr[i];
            if (k == '一' || k == '二' || k == '两' || k == '三' || k == '四'
                    || k == '五' || k == '六' || k == '七' || k == '八' || k == '九'
                    || k == '0' || k == '1' || k == '2' || k == '3' || k == '4'
                    || k == '5' || k == '6' || k == '7' || k == '8' || k == '9') {
                switch (k) {
                    case '一':
                        source = source.replace(k + "", "1");
                        break;
                    case '二':
                        source = source.replace(k + "", "2");
                        break;
                    case '两':
                        source = source.replace(k + "", "2");
                        break;
                    case '三':
                        source = source.replace(k + "", "3");
                        break;
                    case '四':
                        source = source.replace(k + "", "4");
                        break;
                    case '五':
                        source = source.replace(k + "", "5");
                        break;
                    case '六':
                        source = source.replace(k + "", "6");
                        break;
                    case '七':
                        source = source.replace(k + "", "7");
                        break;
                    case '八':
                        source = source.replace(k + "", "8");
                        break;
                    case '九':
                        source = source.replace(k + "", "9");
                        break;
                    default:
                        break;
                }
            } else if ((k == '块' || k == '元' || k == '点' || k == '.') && i != charArr.length - 1) {
                source = source.replace(k, '.');
            } else if (k == '零') {
                source = source.replace(k, '0');
            } else {
                source = source.replace(k + "", "");
            }
        }
        return source.trim();
    }

}
