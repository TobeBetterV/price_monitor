package com.qq.xgdemo.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.tencent.android.tpush.XGPushConfig;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TreeSet;

public class CommonUtil {




    public static void writeResultToLog(String fileName, String content) {

        FileWriter fileWriter = null;
        if (content == null) {
            LogUtil.d("写入的内容是空，请检查收再输入");
            return;
        }
        String SDPATH = Environment.getExternalStorageDirectory() + File.separator + Constants.RESULT_FILE_ROOT + File.separator + fileName + ".txt";
        File file = new File(SDPATH);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //LogUtil.d("sdpath: " + SDPATH);
        try {
            fileWriter = new FileWriter(file, true);

            fileWriter.write(getTime() + content + "\r\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (Throwable e) {
            LogUtil.d("写log文件错误");

        } finally {
            if (null != fileWriter) {
                try {
                    fileWriter.close();
                } catch (Exception e) {

                }
            }
        }

    }

    public static String getTime() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        return df.format(new Date());

    }

    public static String getDelayPushTime() {
        Long time = System.currentTimeMillis();
        LogUtil.d("当前的时间，毫秒 ： " + time);
        Date date = new Date(time + 5 * 60 * 1000);//延迟5分钟
        LogUtil.d("延迟后的时间，毫秒 ： " + (time + 5 * 60 * 1000));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static String getFileNameTime() {

        SimpleDateFormat df = new SimpleDateFormat("_yyyy-MM-dd_HH_mm_ss");
        return df.format(new Date());

    }

    public static String getFileNameDay() {
        SimpleDateFormat df = new SimpleDateFormat("_yyyy-MM-dd");
        return df.format(new Date());
    }

    public static String getLocalMsgTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    public static int[] getLocalMsgHourMin() {
        SimpleDateFormat df = new SimpleDateFormat("HH-mm");
        String time = df.format(new Date());
        String[] timeSplit = time.split("-");
        int hour = Integer.parseInt(timeSplit[0]);
        LogUtil.e("hour : " + hour);
        int min = Integer.parseInt(timeSplit[1]);
        LogUtil.e("min : " + min);
        //延迟5分钟启动，如果时间超过当前的分钟，那么小时数加1
        if (min + 5 >= 60) {
            min = min + 5 - 60;
            hour = hour + 1;
        } else {
            min = min + 5;
        }
        LogUtil.e("after_hour : " + hour);
        LogUtil.e("after_min : " + min);
        return new int[]{hour, min};
    }


    public static String getRandomString(int len) {

        String keyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int length = keyString.length();

        for (int i = 0; i < len; i++) {
            sb.append(keyString.charAt((int) Math.round(Math.random() * (length - 1))));
        }
        return sb.toString();

    }

    public static String getRandomStringChinese(int len) {
        String keyString = "喜欢一个人久了这种喜欢会自然而然的变成一种习惯并不是不爱了只是当初的新鲜感没有了\n" +
                "取而代之的是生长在骨子里的感情 或许你愚昧 认为没有了当初的感觉 热情总会变冷淡 但请\n" +
                "你想清楚你们经历过多少才走到现在 喜欢到习惯是一个漫长的过程 漫长的都不被你发觉 就像\n" +
                "你的表 戴久了会成为习惯 你却觉得它没有了当初的光泽 觉得没那么喜欢它了 你扔掉了它 才\n" +
                "发现不习惯 你后悔的时候却早已经找不到它了 我明白长久的感情不容易 我知道好伴侣应该被\n" +
                "珍惜 我清楚陪伴是最长情的告白 爱是陪伴 是不管他需要与否你都在 不是嘴巴说了就是爱了";

        StringBuffer sb = new StringBuffer();
        int length = keyString.length();

        for (int i = 0; i < len; i++) {
            sb.append(keyString.charAt((int) Math.round(Math.random() * (length - 1))));
        }
        return sb.toString();
    }

    public static String getToken(Context context) {

        String token = SharePreferenceUtil.getStoredToken(context);

        LogUtil.d("注册返回的token: " + token);

        if (TextUtils.isEmpty(token)) {
            token = XGPushConfig.getToken(context);
            LogUtil.d("本地缓存的token: " + token);
        }
        return token;
    }


    public static int parseNum(String title) {

        if (TextUtils.isEmpty(title)) {
            return -1;
        }
        if (!title.contains("timeAndArriveTestTitle")) {
            return -2;
        }
        String sub = title.substring(22);
        LogUtil.d("subNum " + sub);

        return Integer.parseInt(sub);
    }

    public static String getComplex(int len) {
        String source = "醫之爲技盖甚難者也有屬於已者有屬於人者古書難讀且多錯簡訛脱欲技術之精首在讀書文學不深造不能讀也病情萬殊决於俄頃生理神祕輒多疑似不明决不足肆應必學識兼到方可與言明决也吾生有涯而知無涯故曰業精於勤荒於嬉然非有道之士以名山自期者不足與言無逸也言醫者尚師承所贵乎師者不惟其名惟其學而拾取一二成方享盛名者比比盖非聰明绝世不能自得師也凡此皆所謂難也雖然此屬於已者苟刻苦自勵未嘗不求仁得仁其屬于人者则權不我操矣其事乃不勝更僕如猝病之危者一日恒數變而病家日延醫一次而醫之技窮病室宜清潔而病者所居湫隘穢氣充塞而醫之技窮病忌勞服食服病者屢犯之而醫之技窮尤無可如何者病家挾成見醫以爲宜補也而病者畏參如虎醫以為宜汗下也而病家以爲虚甚附子麻黄则减其分量人参白术無故增益之而醫之技愈窮其有病者喘息待斃醫者三五七人从容議藥言人人殊病家不知所可则决之診金多寡與醫衣飾輿馬之豐嗇而醫之技乃無不窮之又窮準此以言醫窮人難醫富人尤難也而先生之醫案自天子以至王公大臣居全書之泰半吾所視爲最難者先生盖行無所事焉夫豈無故然耶至其案語之中庸用藥之淵博於長洲以下乃至金元四家乃至王海藏張隐菴諸大家之外别开生面全無劍拔弩張面目使病家望之生畏者則其所學甯可量邪闻之陳氏治醫至先生凡十九世嗚呼如是其久遠也不惟於醫學即人情世事閱歴之深誰出其右者宜乎無美不臻也猶憶十數年前曾一親先生杖履并拜珍物之賜今何幸而得拜觀遗著也吾知此篇一出其必風行海内也夫";
        StringBuilder result = new StringBuilder();
        int length = source.length();
        for (int i = 0; i < len; i++) {

            int temp = new Random().nextInt(length);
            LogUtil.d("随机数 :" + temp);
            result.append(source.charAt(temp));

        }
        LogUtil.d("字符串 :" + result.toString());
        return result.toString();

    }

    public static String getSpeak(int len) {
        String source = "莪説檤，“妑妑，沵趉妑。”彵朢車迯看孒看，説，“莪荬凢個橘ふ厾。沵僦茬泚哋，芣婹趉憅。”莪看哪笾仴珆哋栅孄迯洧凢個賣崬覀哋等着顧愙。趉菿哪笾仴珆，湏瑏過鉄檤，湏朓芐厾叒瓟仩厾。父儭湜①個眫ふ，趉過厾洎嘫婹曊倳些。莪夲唻婹厾哋，彵芣肻，呮恏讓彵厾。莪看見彵瀻着嫼鈽尒萺，瑏着嫼鈽汏骉啩，堔圊鈽婂垉，蹣跚哋趉菿鉄檤笾，嫚嫚探裑芐厾，尙芣汏難。妸湜彵瑏過鉄檤，婹瓟仩哪笾仴珆，僦芣嫆昜孒。彵鼡倆掱襻着仩媔，倆腳侢姠仩縮；彵萉眫哋裑ふ姠咗嶶傾，显炪怓劦哋樣ふ。適溡莪看見彵哋揹影，莪哋汨佷赽哋蓅芐唻孒。莪迀緊拭迀孒汨啪彵看見竾啪莂亾看見。莪侢姠迯看溡彵巳砲孒咮葒哋橘ふ朢冋趉孒過鉄檤溡彵姺將橘ふ潵倣茬哋仩洎己嫚嫚瓟芐侢砲起橘ふ趉菿適笾溡，莪迀緊厾搀彵彵啝莪趉菿車仩將橘ふ①骰悩ル倣茬莪哋怶汏扆仩。纡湜圤圤扆仩哋狔汢，杺里佷輕菘姒哋，過①浍説莪趉孒菿哪笾唻莪朢着彵趉炪厾彵趉孒凢荹冋過頭看見莪説琎厾，里笾莈亾等彵哋揹影婫叺唻唻暀暀哋亾里，侢找芣着孒，莪楩琎唻唑芐，莪哋眼汨叒唻孒";
        StringBuilder result = new StringBuilder();
        int length = source.length();
        for (int i = 0; i < len; i++) {

            int temp = new Random().nextInt(length);
            LogUtil.d("随机数 :" + temp);
            result.append(source.charAt(temp));

        }
        LogUtil.d("字符串 :" + result.toString());
        return result.toString();
    }

    public static String getSpecificSymbol(int len) {
        String source = "①②③④⑤⑥⑦⑧⑨⑩ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩ㍿▓♨♛❖♓☪✙┉┋☹☺☻تヅツッシÜϡﭢ™℠℗©®♥❤❥❣❦❧♡۵웃유ღ♋♂♀☿☼☀☁☂☄☾☽❄☃☈⊙☉℃℉❅✺ϟ☇♤♧♡♢♠♣♥♦☜☞☝✍☚☛☟✌✽✾✿❁❃❋❀⚘☑✓✔√☐☒✗✘ㄨ✕✖✖⋆✢✣✤✥❋✦✧✩✰✪✫✬✭✮✯❂✡★✱✲✳✴✵✶✷✸✹✺✻✼❄❅❆❇❈❉❊†☨✞✝☥☦☓☩☯☧☬☸✡♁✙♆。，、＇：∶；?‘’“”〝〞ˆˇ﹕︰﹔﹖﹑•¨….¸;！´？！～—ˉ｜‖＂〃｀@﹫¡¿﹏﹋﹌︴々﹟#﹩$﹠&﹪%*﹡﹢﹦﹤‐￣¯―﹨ˆ˜﹍﹎+=<＿_-ˇ~﹉﹊（）〈〉‹›﹛﹜『』〖〗［］《》〔〕{}「」【】︵︷︿︹︽♠♣♧♡♥❤❥❣♂♀✲☀☼☾☽◐◑☺☻☎☏✿❀№↑↓←→√×÷★℃℉°◆◇⊙■□△▽¿½☯✡㍿卍卐♂♀✚〓㎡♪♫♩♬㊚㊛囍㊒㊖Φ♀♂‖$@*&#※卍卐Ψ♫♬♭♩♪♯♮⌒¶∮‖€￡¥$";
        StringBuilder result = new StringBuilder();
        int length = source.length();
        for (int i = 0; i < len; i++) {

            int temp = new Random().nextInt(length);
            LogUtil.d("随机数 :" + temp);
            result.append(source.charAt(temp));

        }
        LogUtil.d("字符串 :" + result.toString());
        return result.toString();
    }

    public static String getSpecificCombo(int len) {
        String source = "♛❖♓☪✙┉┋☹☺☻تヅツッシÜϡﭢ™℠℗©®♥❤❥❣❦❧♡۵웃유ღ♋♂♀☿☼☀☁☂☄☾莪荬凢個橘ふ厾。沵僦茬泚哋，芣婹趉憅。”莪看哪笾仴珆哋栅孄迯洧凢個賣崬覀哋等着顧愙。趉菿哪笾橘。妳就茬此的，不要走動。”我看哪邊月臺的柵欄外有幾個賣東西的等著顧客。走到哪邊月臺，須穿過鐵道，須跳芐去雙爬上去。親是壹個，過要費事些來，好讓。見他戴著黑鈽爾冒，穿著黑鈽大馬掛深青鈽錦咆，蹣跚仴珆，湏瑏過鉄檤，湏朓芐厾叒瓟仩厾。父儭湜①個眫ふ，趉過厾洎嘫婹曊倳些。莪夲唻婹厾哋，彵芣肻，呮恏讓彵厾。莪看☽❄☃☈⊙☉℃℉❅✺ϟ☇♤♧♡♢♠♣♥♦☜☞☝✍☚☛☟✌✽✾✿❁❃❋❀⚘☑✓✔√☐☒✗✘ㄨ✕✖✖⋆✢✣✤✥❋✦✧✩✰✪✫✬✭✮✯❂✡★✱✲✳✴✵✶✷✸✹✺✻✼❄❅❆❇❈❉❊†☨✞✝☥☦☓☩☯☧☬";
        StringBuilder result = new StringBuilder();
        int length = source.length();
        for (int i = 0; i < len; i++) {

            int temp = new Random().nextInt(length);
            LogUtil.d("随机数 :" + temp);
            result.append(source.charAt(temp));

        }
        LogUtil.d("字符串 :" + result.toString());
        return result.toString();
    }

    public static TreeSet<String> getTagsSet(int number){
        TreeSet<String> set  = new TreeSet();

        if(number<=0){
            return null;
        }
        for(int i=0;i<number;i++){
            String tag = "agtestTag_"+i+"_"+getRandomString(5);
            LogUtil.d("新增的标签是：tag : "+tag);

            set.add(tag);
        }

        return set;

    }


}
