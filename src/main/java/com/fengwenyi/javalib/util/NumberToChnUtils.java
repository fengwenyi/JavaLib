package com.fengwenyi.javalib.util;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/12 12:34
 */
public class NumberToChnUtils {
    static String CHN_NUMBER[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    static String CHN_UNIT[] = {"", "十", "百", "千"};          //权位
    static String CHN_UNIT_SECTION[] = {"", "万", "亿", "万亿"}; //节权位

    /**
     * 测试数据的数据类型
     */
    public static class Test_Data{
        int number;
        String chnNum;
        public Test_Data(int number,String chnNum){
            this.chnNum=chnNum;
            this.number=number;
        }
    }

    /**
     * 测试数据
     */
    static Test_Data testData[]={
            new Test_Data(0,"零"),
            new Test_Data(1,"一"),
            new Test_Data(2,"二"),
            new Test_Data(3, "三"),
            new Test_Data(4, "四"),
            new Test_Data(5, "五"),
            new Test_Data(6, "六"),
            new Test_Data(7, "七"),
            new Test_Data(8, "八"),
            new Test_Data(9, "九"),
            new Test_Data(10, "一十"),
            new Test_Data(11, "一十一"),
            new Test_Data(110, "一百一十"),
            new Test_Data(111, "一百一十一"),
            new Test_Data(100, "一百"),
            new Test_Data(102, "一百零二"),
            new Test_Data(1020, "一千零二十"),
            new Test_Data(1001, "一千零一"),
            new Test_Data(1015, "一千零一十五"),
            new Test_Data(1000, "一千"),
            new Test_Data(10000, "一万"),
            new Test_Data(20010, "二万零一十"),
            new Test_Data(20001, "二万零一"),
            new Test_Data(100000, "一十万"),
            new Test_Data(1000000, "一百万"),
            new Test_Data(10000000, "一千万"),
            new Test_Data(100000000, "一亿"),
            new Test_Data(1000000000, "一十亿"),
            new Test_Data(1000001000, "一十亿一千"),
            new Test_Data(1000000100, "一十亿零一百"),
            new Test_Data(200010, "二十万零一十"),
            new Test_Data(2000105, "二百万零一百零五"),
            new Test_Data(20001007, "二千万一千零七"),
            new Test_Data(2000100190, "二十亿零一十万零一百九十"),
            new Test_Data(1040010000, "一十亿四千零一万"),
            new Test_Data(200012301, "二亿零一万二千三百零一"),
            new Test_Data(2005010010, "二十亿零五百零一万零一十")
//            new Test_Data(4009060200, "四十亿零九百零六万零二百"),
//            new Test_Data(4294967295, "四十二亿九千四百九十六万七千二百九十五")


    };

    /**
     * 阿拉伯数字转换为中文数字的核心算法实现。
     * @param num 为需要转换为中文数字的阿拉伯数字，是无符号的整形数
     * @return [忽略]
     */
    public static String NumberToChn(int num) {
        StringBuffer returnStr = new StringBuffer();
        Boolean needZero = false;
        int pos=0;           //节权位的位置
        if(num==0){
            //如果num为0，进行特殊处理。
            returnStr.insert(0,CHN_NUMBER[0]);
        }
        while (num > 0) {
            int section = num % 10000;
            if (needZero) {
                returnStr.insert(0, CHN_NUMBER[0]);
            }
            String sectionToChn = SectionNumToChn(section);
            //判断是否需要节权位
            sectionToChn += (section != 0) ? CHN_UNIT_SECTION[pos] : CHN_UNIT_SECTION[0];
            returnStr.insert(0, sectionToChn);
            needZero = ((section < 1000 && section > 0) ? true : false); //判断section中的千位上是不是为零，若为零应该添加一个零。
            pos++;
            num = num / 10000;
        }
        return returnStr.toString();
    }

    /**
     * 将四位的section转换为中文数字
     * @param section [忽略]
     * @return [忽略]
     */
    public static String SectionNumToChn(int section) {
        StringBuffer returnStr = new StringBuffer();
        int unitPos = 0;       //节权位的位置编号，0-3依次为个十百千;

        Boolean zero = true;
        while (section > 0) {

            int v = (section % 10);
            if (v == 0) {
                if ((section == 0) || !zero) {
                    zero = true; /*需要补0，zero的作用是确保对连续的多个0，只补一个中文零*/
                    //chnStr.insert(0, chnNumChar[v]);
                    returnStr.insert(0, CHN_NUMBER[v]);
                }
            } else {
                zero = false; //至少有一个数字不是0
                StringBuffer tempStr = new StringBuffer(CHN_NUMBER[v]);//数字v所对应的中文数字
                tempStr.append(CHN_UNIT[unitPos]);  //数字v所对应的中文权位
                returnStr.insert(0, tempStr);
            }
            unitPos++; //移位
            section = section / 10;
        }
        return returnStr.toString();
    }

    /**
     * 完成将阿拉伯数字转换为中文数字的测试
     */
    public static void TestNumToChn(){
        for(int i=0;i<testData.length;i++) {
            String str=NumberToChn(testData[i].number);
            System.out.println(testData[i].number+"\t"+testData[i].chnNum+"\t"+str+"\t"+str.equals(testData[i].chnNum));
        }
    }


    /**
     * 中文转换成阿拉伯数字，中文字符串除了包括0-9的中文汉字，还包括十，百，千，万等权位。
     * 此处是完成对这些权位的类型定义。
     * name是指这些权位的汉字字符串。
     * value是指权位多对应的数值的大小。诸如：十对应的值的大小为10，百对应为100等
     * secUnit若为true，代表该权位为节权位，即万，亿，万亿等
     */
    public static class Chn_Name_value{
        String name;
        int value;
        Boolean secUnit;
        public Chn_Name_value(String name,int value,Boolean secUnit){
            this.name=name;
            this.value=value;
            this.secUnit=secUnit;
        }
    }

    static Chn_Name_value chnNameValue[]={
            new Chn_Name_value("十",10,false),
            new Chn_Name_value("百",100,false),
            new Chn_Name_value("千",1000,false),
            new Chn_Name_value("万",10000,true),
            new Chn_Name_value("亿",100000000,true)
    };

    /**
     * 返回中文数字汉字所对应的阿拉伯数字，若str不为中文数字，则返回-1
     * @param str
     * @return
     */
    public static int ChnNumToValue(String str){
        for(int i=0;i<CHN_NUMBER.length;i++){
            if(str.equals(CHN_NUMBER[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回中文汉字权位在chnNameValue数组中所对应的索引号，若不为中文汉字权位，则返回-1
     * @param str
     * @return
     */
    public static int ChnUnitToValue(String str){
        for(int i=0;i<chnNameValue.length;i++){
            if(str.equals(chnNameValue[i].name)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回中文数字字符串所对应的int类型的阿拉伯数字
     * @param str
     * @return
     */
    public static int ChnStringToNumber(String str){
        int returnNumber=0;
        int section=0;
        int pos=0;
        int number=0;
        while (pos<str.length()){
            int num=ChnNumToValue(str.substring(pos,pos+1));
            //若num>=0，代表该位置（pos），所对应的是数字不是权位。若小于0，则表示为权位
            if(num>=0){
                number=num;
                pos++;
                //pos是最好一位，直接将number加入到section中。
                if(pos>=str.length()){
                    section+=number;
                    returnNumber+=section;
                    break;
                }
            }else{
                int chnNameValueIndex=ChnUnitToValue(str.substring(pos,pos+1));
                //chnNameValue[chnNameValueIndex].secUnit==true，表示该位置所对应的权位是节权位，
                if(chnNameValue[chnNameValueIndex].secUnit){
                    section=(section+number)*chnNameValue[chnNameValueIndex].value;
                    returnNumber+=section;
                    section=0;
                }else{
                    section+=number*chnNameValue[chnNameValueIndex].value;
                }
                pos++;
                number=0;
                if(pos>=str.length()){
                    returnNumber+=section;
                    break;
                }
            }
        }
        return returnNumber;
    }

    /**
     * 完成对中文数字字符串转换成阿拉伯数字函数的测试
     */
    public static void TestChnStringToNumber(){
        for(int i=0;i<testData.length;i++){
            int number=ChnStringToNumber(testData[i].chnNum);
            System.out.println(testData[i].chnNum+"\t"+number+"\t"+testData[i].number+"\t"+(number==testData[i].number));

        }
    }
}
