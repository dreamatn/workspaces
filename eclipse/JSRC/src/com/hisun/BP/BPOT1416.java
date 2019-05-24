package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1416 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CLASS_MASK = "000102101112131415161718191A1B1C1D1E1F1G1Z202122232425303132##";
    String DM_TYPE_MASK = "012#";
    String MANT_MASK = "012#";
    String USE_LVL_MASK = "0123#";
    String STORE_MASK = "0123#";
    String DOWN_FLAG_MASK = "YN#";
    String HISYEAR_MASK = "DMY#";
    String DEL_FLAG_MASK = "YN#";
    String DUP_FLAG_MASK = "YN#";
    BPOT1416_WS_VARIABLES WS_VARIABLES = new BPOT1416_WS_VARIABLES();
    BPOT1416_WS_VIEW_DATA WS_VIEW_DATA = new BPOT1416_WS_VIEW_DATA();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPARP BPRPARP = new BPRPARP();
    BPCRBPRP BPCRBPRP = new BPCRBPRP();
    BPCOBPRP BPCOBPRP = new BPCOBPRP();
    SCCGWA SCCGWA;
    BPB1420_AWA_1420 AWA_1420;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1416 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_1420 = new BPB1420_AWA_1420();
        IBS.init(SCCGWA, AWA_1420);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_1420);
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_VIEW_DATA);
        IBS.init(SCCGWA, BPCRBPRP);
        IBS.CPY2CLS(SCCGWA, AWA_1420.MASK, WS_VIEW_DATA);
        CEP.TRC(SCCGWA, AWA_1420.MASK);
        if (AWA_1420.MASK == null) AWA_1420.MASK = "";
        JIBS_tmp_int = AWA_1420.MASK.length();
        for (int i=0;i<400-JIBS_tmp_int;i++) AWA_1420.MASK += " ";
        CEP.TRC(SCCGWA, AWA_1420.MASK.substring(0, 50));
        if (AWA_1420.MASK == null) AWA_1420.MASK = "";
        JIBS_tmp_int = AWA_1420.MASK.length();
        for (int i=0;i<400-JIBS_tmp_int;i++) AWA_1420.MASK += " ";
        CEP.TRC(SCCGWA, AWA_1420.MASK.substring(51 - 1, 51 + 50 - 1));
        if (AWA_1420.MASK == null) AWA_1420.MASK = "";
        JIBS_tmp_int = AWA_1420.MASK.length();
        for (int i=0;i<400-JIBS_tmp_int;i++) AWA_1420.MASK += " ";
        CEP.TRC(SCCGWA, AWA_1420.MASK.substring(101 - 1, 101 + 50 - 1));
        if (AWA_1420.MASK == null) AWA_1420.MASK = "";
        JIBS_tmp_int = AWA_1420.MASK.length();
        for (int i=0;i<400-JIBS_tmp_int;i++) AWA_1420.MASK += " ";
        CEP.TRC(SCCGWA, AWA_1420.MASK.substring(151 - 1, 151 + 50 - 1));
        CEP.TRC(SCCGWA, WS_VIEW_DATA.STS);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.WS_CLASS.CLASS1);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.WS_CLASS.CLASS2);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.WS_CLASS.CLASS3);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.WS_CLASS.CLASS4);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.DM_TYPE);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.MANT);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.USE_LVL);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.STORE);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.DOWN_FLAG);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.HISYEAR);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.DEL_FLG);
        CEP.TRC(SCCGWA, WS_VIEW_DATA.DUP_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBPRP);
        IBS.init(SCCGWA, BPRPARP);
        BPCRBPRP.LEN = 272;
        CEP.TRC(SCCGWA, BPCRBPRP.LEN);
        BPCRBPRP.PTR = BPRPARP;
        BPCRBPRP.FUNC = 'S';
        S000_CALL_BPZRBPRP();
        if (pgmRtn) return;
        R000_WRITE_TITLE();
        if (pgmRtn) return;
        while (BPCRBPRP.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            BPCRBPRP.FUNC = 'R';
            S000_CALL_BPZRBPRP();
            if (pgmRtn) return;
            if (BPCRBPRP.RETURN_INFO != 'N') {
                T000_CHECK_VIEW();
                if (pgmRtn) return;
                if (WS_VARIABLES.FND_VIEW_FLG == 'Y') {
                    R000_OUTPUT_DATA_PROCESS();
                    if (pgmRtn) return;
                    R000_WRITE_TS();
                    if (pgmRtn) return;
                }
            }
        }
        BPCRBPRP.FUNC = 'E';
        S000_CALL_BPZRBPRP();
        if (pgmRtn) return;
    }
    public void T000_CHECK_VIEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VIEW_DATA.STS);
        if (WS_VIEW_DATA.STS.equalsIgnoreCase("00")
            || WS_VIEW_DATA.STS.equalsIgnoreCase("11")) {
            WS_VARIABLES.FND_VIEW_FLG = 'Y';
        } else if (WS_VIEW_DATA.STS.equalsIgnoreCase("10")) {
            if (BPRPARP.STS == 'Y') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            } else {
                WS_VARIABLES.FND_VIEW_FLG = 'N';
            }
        } else if (WS_VIEW_DATA.STS.equalsIgnoreCase("01")) {
            if (BPRPARP.STS == 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            } else {
                WS_VARIABLES.FND_VIEW_FLG = 'N';
            }
        } else {
        }
        CEP.TRC(SCCGWA, WS_VIEW_DATA.WS_CLASS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VIEW_DATA.WS_CLASS);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_VIEW_DATA.WS_CLASS);
        if (JIBS_tmp_str[0].equalsIgnoreCase("000000000000000000000000000000") 
            || JIBS_tmp_str[2].equalsIgnoreCase("111111111111111111111111111111")) {
            if (WS_VARIABLES.FND_VIEW_FLG != 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            }
        } else {
            WS_VARIABLES.END_PERFORM_FLG = 'N';
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.END_PERFORM_FLG != 'Y' 
                && WS_VARIABLES.FND_VIEW_FLG != 'N'; WS_VARIABLES.CNT += 2) {
                CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                CEP.TRC(SCCGWA, BPRPARP.CLASS);
                if (CLASS_MASK == null) CLASS_MASK = "";
                JIBS_tmp_int = CLASS_MASK.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CLASS_MASK += " ";
                CEP.TRC(SCCGWA, CLASS_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 2 - 1));
                if (CLASS_MASK == null) CLASS_MASK = "";
                JIBS_tmp_int = CLASS_MASK.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CLASS_MASK += " ";
                if (BPRPARP.CLASS.equalsIgnoreCase(CLASS_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 2 - 1))) {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                    WS_VARIABLES.J = ( WS_VARIABLES.CNT + 1 ) / 2;
                    CEP.TRC(SCCGWA, WS_VARIABLES.J);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VIEW_DATA.WS_CLASS);
                    CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(WS_VARIABLES.J - 1, WS_VARIABLES.J + 1 - 1));
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VIEW_DATA.WS_CLASS);
                    if (JIBS_tmp_str[0].substring(WS_VARIABLES.J - 1, WS_VARIABLES.J + 1 - 1).equalsIgnoreCase("1")) {
                        WS_VARIABLES.FND_VIEW_FLG = 'Y';
                        WS_VARIABLES.END_PERFORM_FLG = 'Y';
                    }
                }
                if (WS_VARIABLES.CNT > 60) {
                    WS_VARIABLES.FND_VIEW_FLG = 'N';
                    WS_VARIABLES.END_PERFORM_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, BPRPARP.DM_TYPE);
        if (WS_VIEW_DATA.DM_TYPE.equalsIgnoreCase("000") 
            || WS_VIEW_DATA.DM_TYPE.equalsIgnoreCase("111")) {
            if (WS_VARIABLES.FND_VIEW_FLG != 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            }
        } else {
            WS_VARIABLES.END_PERFORM_FLG = 'N';
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.END_PERFORM_FLG != 'Y' 
                && WS_VARIABLES.FND_VIEW_FLG != 'N'; WS_VARIABLES.CNT += 1) {
                if (DM_TYPE_MASK == null) DM_TYPE_MASK = "";
                JIBS_tmp_int = DM_TYPE_MASK.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) DM_TYPE_MASK += " ";
                if (BPRPARP.DM_TYPE == DM_TYPE_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1)) {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                    if (WS_VIEW_DATA.DM_TYPE == null) WS_VIEW_DATA.DM_TYPE = "";
                    JIBS_tmp_int = WS_VIEW_DATA.DM_TYPE.length();
                    for (int i=0;i<3-JIBS_tmp_int;i++) WS_VIEW_DATA.DM_TYPE += " ";
                    if (WS_VIEW_DATA.DM_TYPE.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1).equalsIgnoreCase("1")) {
                        WS_VARIABLES.FND_VIEW_FLG = 'Y';
                        WS_VARIABLES.END_PERFORM_FLG = 'Y';
                    }
                }
                if (WS_VARIABLES.CNT > 3) {
                    WS_VARIABLES.FND_VIEW_FLG = 'N';
                    WS_VARIABLES.END_PERFORM_FLG = 'Y';
                }
            }
        }
        if (WS_VIEW_DATA.MANT.equalsIgnoreCase("000") 
            || WS_VIEW_DATA.MANT.equalsIgnoreCase("111")) {
            if (WS_VARIABLES.FND_VIEW_FLG != 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            }
        } else {
            WS_VARIABLES.END_PERFORM_FLG = 'N';
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.END_PERFORM_FLG != 'Y' 
                && WS_VARIABLES.FND_VIEW_FLG != 'N'; WS_VARIABLES.CNT += 1) {
                CEP.TRC(SCCGWA, BPRPARP.MANT);
                if (MANT_MASK == null) MANT_MASK = "";
                JIBS_tmp_int = MANT_MASK.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) MANT_MASK += " ";
                if (BPRPARP.MANT == MANT_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1)) {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                    if (WS_VIEW_DATA.MANT == null) WS_VIEW_DATA.MANT = "";
                    JIBS_tmp_int = WS_VIEW_DATA.MANT.length();
                    for (int i=0;i<3-JIBS_tmp_int;i++) WS_VIEW_DATA.MANT += " ";
                    if (WS_VIEW_DATA.MANT.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1).equalsIgnoreCase("1")) {
                        WS_VARIABLES.FND_VIEW_FLG = 'Y';
                        WS_VARIABLES.END_PERFORM_FLG = 'Y';
                    }
                }
                if (WS_VARIABLES.CNT > 3) {
                    WS_VARIABLES.FND_VIEW_FLG = 'N';
                    WS_VARIABLES.END_PERFORM_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, BPRPARP.USE_LVL);
        if (WS_VIEW_DATA.USE_LVL.equalsIgnoreCase("0000") 
            || WS_VIEW_DATA.USE_LVL.equalsIgnoreCase("1111")) {
            if (WS_VARIABLES.FND_VIEW_FLG != 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            }
        } else {
            WS_VARIABLES.END_PERFORM_FLG = 'N';
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.END_PERFORM_FLG != 'Y' 
                && WS_VARIABLES.FND_VIEW_FLG != 'N'; WS_VARIABLES.CNT += 1) {
                if (USE_LVL_MASK == null) USE_LVL_MASK = "";
                JIBS_tmp_int = USE_LVL_MASK.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) USE_LVL_MASK += " ";
                if (BPRPARP.USE_LVL == USE_LVL_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1)) {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                    if (WS_VIEW_DATA.USE_LVL == null) WS_VIEW_DATA.USE_LVL = "";
                    JIBS_tmp_int = WS_VIEW_DATA.USE_LVL.length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) WS_VIEW_DATA.USE_LVL += " ";
                    if (WS_VIEW_DATA.USE_LVL.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1).equalsIgnoreCase("1")) {
                        WS_VARIABLES.FND_VIEW_FLG = 'Y';
                        WS_VARIABLES.END_PERFORM_FLG = 'Y';
                    }
                }
                if (WS_VARIABLES.CNT > 4) {
                    WS_VARIABLES.FND_VIEW_FLG = 'N';
                    WS_VARIABLES.END_PERFORM_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, BPRPARP.STORE);
        if (WS_VIEW_DATA.STORE.equalsIgnoreCase("0000") 
            || WS_VIEW_DATA.STORE.equalsIgnoreCase("1111")) {
            if (WS_VARIABLES.FND_VIEW_FLG != 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            }
        } else {
            WS_VARIABLES.END_PERFORM_FLG = 'N';
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.END_PERFORM_FLG != 'Y' 
                && WS_VARIABLES.FND_VIEW_FLG != 'N'; WS_VARIABLES.CNT += 1) {
                if (STORE_MASK == null) STORE_MASK = "";
                JIBS_tmp_int = STORE_MASK.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) STORE_MASK += " ";
                if (BPRPARP.STORE == STORE_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1)) {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                    if (WS_VIEW_DATA.STORE == null) WS_VIEW_DATA.STORE = "";
                    JIBS_tmp_int = WS_VIEW_DATA.STORE.length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) WS_VIEW_DATA.STORE += " ";
                    if (WS_VIEW_DATA.STORE.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1).equalsIgnoreCase("1")) {
                        WS_VARIABLES.FND_VIEW_FLG = 'Y';
                        WS_VARIABLES.END_PERFORM_FLG = 'Y';
                    }
                }
                if (WS_VARIABLES.CNT > 4) {
                    WS_VARIABLES.FND_VIEW_FLG = 'N';
                    WS_VARIABLES.END_PERFORM_FLG = 'Y';
                }
            }
        }
        if (WS_VIEW_DATA.DOWN_FLAG.equalsIgnoreCase("00") 
            || WS_VIEW_DATA.DOWN_FLAG.equalsIgnoreCase("11")) {
            if (WS_VARIABLES.FND_VIEW_FLG != 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            }
        } else {
            WS_VARIABLES.END_PERFORM_FLG = 'N';
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.END_PERFORM_FLG != 'Y' 
                && WS_VARIABLES.FND_VIEW_FLG != 'N'; WS_VARIABLES.CNT += 1) {
                if (DOWN_FLAG_MASK == null) DOWN_FLAG_MASK = "";
                JIBS_tmp_int = DOWN_FLAG_MASK.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) DOWN_FLAG_MASK += " ";
                if (BPRPARP.DOWN_FLAG == DOWN_FLAG_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1)) {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                    if (WS_VIEW_DATA.DOWN_FLAG == null) WS_VIEW_DATA.DOWN_FLAG = "";
                    JIBS_tmp_int = WS_VIEW_DATA.DOWN_FLAG.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) WS_VIEW_DATA.DOWN_FLAG += " ";
                    if (WS_VIEW_DATA.DOWN_FLAG.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1).equalsIgnoreCase("1")) {
                        WS_VARIABLES.FND_VIEW_FLG = 'Y';
                        WS_VARIABLES.END_PERFORM_FLG = 'Y';
                    }
                }
                if (WS_VARIABLES.CNT > 2) {
                    WS_VARIABLES.FND_VIEW_FLG = 'N';
                    WS_VARIABLES.END_PERFORM_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, BPRPARP.HISYEAR);
        if (WS_VIEW_DATA.HISYEAR.equalsIgnoreCase("000") 
            || WS_VIEW_DATA.HISYEAR.equalsIgnoreCase("111")) {
            if (WS_VARIABLES.FND_VIEW_FLG != 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            }
        } else {
            WS_VARIABLES.END_PERFORM_FLG = 'N';
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.END_PERFORM_FLG != 'Y' 
                && WS_VARIABLES.FND_VIEW_FLG != 'N'; WS_VARIABLES.CNT += 1) {
                if (BPRPARP.HISYEAR == null) BPRPARP.HISYEAR = "";
                JIBS_tmp_int = BPRPARP.HISYEAR.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) BPRPARP.HISYEAR += " ";
                if (HISYEAR_MASK == null) HISYEAR_MASK = "";
                JIBS_tmp_int = HISYEAR_MASK.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) HISYEAR_MASK += " ";
                if (BPRPARP.HISYEAR.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(HISYEAR_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1))) {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                    if (WS_VIEW_DATA.HISYEAR == null) WS_VIEW_DATA.HISYEAR = "";
                    JIBS_tmp_int = WS_VIEW_DATA.HISYEAR.length();
                    for (int i=0;i<3-JIBS_tmp_int;i++) WS_VIEW_DATA.HISYEAR += " ";
                    if (WS_VIEW_DATA.HISYEAR.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1).equalsIgnoreCase("1")) {
                        WS_VARIABLES.FND_VIEW_FLG = 'Y';
                        WS_VARIABLES.END_PERFORM_FLG = 'Y';
                    }
                }
                if (WS_VARIABLES.CNT > 3) {
                    WS_VARIABLES.FND_VIEW_FLG = 'N';
                    WS_VARIABLES.END_PERFORM_FLG = 'Y';
                }
            }
        }
        if (WS_VIEW_DATA.DEL_FLG.equalsIgnoreCase("00") 
            || WS_VIEW_DATA.DEL_FLG.equalsIgnoreCase("11")) {
            if (WS_VARIABLES.FND_VIEW_FLG != 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            }
        } else {
            CEP.TRC(SCCGWA, WS_VIEW_DATA.DEL_FLG);
            WS_VARIABLES.END_PERFORM_FLG = 'N';
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.END_PERFORM_FLG != 'Y' 
                && WS_VARIABLES.FND_VIEW_FLG != 'N'; WS_VARIABLES.CNT += 1) {
                CEP.TRC(SCCGWA, BPRPARP.DEL_FLG);
                if (DEL_FLAG_MASK == null) DEL_FLAG_MASK = "";
                JIBS_tmp_int = DEL_FLAG_MASK.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) DEL_FLAG_MASK += " ";
                CEP.TRC(SCCGWA, DEL_FLAG_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1));
                if (DEL_FLAG_MASK == null) DEL_FLAG_MASK = "";
                JIBS_tmp_int = DEL_FLAG_MASK.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) DEL_FLAG_MASK += " ";
                if (BPRPARP.DEL_FLG == DEL_FLAG_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1)) {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                    if (WS_VIEW_DATA.DEL_FLG == null) WS_VIEW_DATA.DEL_FLG = "";
                    JIBS_tmp_int = WS_VIEW_DATA.DEL_FLG.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) WS_VIEW_DATA.DEL_FLG += " ";
                    if (WS_VIEW_DATA.DEL_FLG.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1).equalsIgnoreCase("1")) {
                        WS_VARIABLES.FND_VIEW_FLG = 'Y';
                        WS_VARIABLES.END_PERFORM_FLG = 'Y';
                    }
                }
                if (WS_VARIABLES.CNT > 2) {
                    WS_VARIABLES.FND_VIEW_FLG = 'N';
                    WS_VARIABLES.END_PERFORM_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_VIEW_DATA.DUP_FLG);
        if (WS_VIEW_DATA.DUP_FLG.equalsIgnoreCase("00") 
            || WS_VIEW_DATA.DUP_FLG.equalsIgnoreCase("11")) {
            if (WS_VARIABLES.FND_VIEW_FLG != 'N') {
                WS_VARIABLES.FND_VIEW_FLG = 'Y';
            }
        } else {
            WS_VARIABLES.END_PERFORM_FLG = 'N';
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.END_PERFORM_FLG != 'Y' 
                && WS_VARIABLES.FND_VIEW_FLG != 'N'; WS_VARIABLES.CNT += 1) {
                CEP.TRC(SCCGWA, BPRPARP.DUP_DATE_FLG);
                if (DUP_FLAG_MASK == null) DUP_FLAG_MASK = "";
                JIBS_tmp_int = DUP_FLAG_MASK.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) DUP_FLAG_MASK += " ";
                CEP.TRC(SCCGWA, DUP_FLAG_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1));
                if (DUP_FLAG_MASK == null) DUP_FLAG_MASK = "";
                JIBS_tmp_int = DUP_FLAG_MASK.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) DUP_FLAG_MASK += " ";
                if (BPRPARP.DUP_DATE_FLG == DUP_FLAG_MASK.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1)) {
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                    if (WS_VIEW_DATA.DUP_FLG == null) WS_VIEW_DATA.DUP_FLG = "";
                    JIBS_tmp_int = WS_VIEW_DATA.DUP_FLG.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) WS_VIEW_DATA.DUP_FLG += " ";
                    if (WS_VIEW_DATA.DUP_FLG.substring(WS_VARIABLES.CNT - 1, WS_VARIABLES.CNT + 1 - 1).equalsIgnoreCase("1")) {
                        WS_VARIABLES.FND_VIEW_FLG = 'Y';
                        WS_VARIABLES.END_PERFORM_FLG = 'Y';
                    }
                }
                if (WS_VARIABLES.CNT > 2) {
                    WS_VARIABLES.FND_VIEW_FLG = 'N';
                    WS_VARIABLES.END_PERFORM_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.FND_VIEW_FLG);
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBPRP);
        BPCOBPRP.DATA.KEY.TYPE = BPRPARP.KEY.TYPE;
        BPCOBPRP.DATA.NAME = BPRPARP.NAME;
        BPCOBPRP.DATA.CNAME = BPRPARP.CNAME;
        BPCOBPRP.DATA.DSTORE = BPRPARP.DSTORE;
        BPCOBPRP.DATA.NSTORE = BPRPARP.NSTORE;
        BPCOBPRP.DATA.SDESC = BPRPARP.SDESC;
        BPCOBPRP.DATA.CHK = BPRPARP.CHK;
        BPCOBPRP.DATA.ENA = BPRPARP.ENA;
        BPCOBPRP.DATA.STS = BPRPARP.STS;
        BPCOBPRP.DATA.ATTR = BPRPARP.ATTR;
        BPCOBPRP.DATA.CLASS = BPRPARP.CLASS;
        BPCOBPRP.DATA.DM_TYPE = BPRPARP.DM_TYPE;
        BPCOBPRP.DATA.MANT = BPRPARP.MANT;
        BPCOBPRP.DATA.USE_LVL = BPRPARP.USE_LVL;
        BPCOBPRP.DATA.STORE = BPRPARP.STORE;
        BPCOBPRP.DATA.DOWN_FLAG = BPRPARP.DOWN_FLAG;
        BPCOBPRP.DATA.HISYEAR = BPRPARP.HISYEAR;
        BPCOBPRP.DATA.DEL_FLG = BPRPARP.DEL_FLG;
        BPCOBPRP.DATA.DUP_DATE_FLG = BPRPARP.DUP_DATE_FLG;
        BPCOBPRP.DATA.PARM_VIEW1 = BPRPARP.PARM_VIEW1;
        BPCOBPRP.DATA.PARM_VIEW2 = BPRPARP.PARM_VIEW2;
        BPCOBPRP.DATA.PARM_VIEW3 = BPRPARP.PARM_VIEW3;
        BPCOBPRP.DATA.PARM_VIEW4 = BPRPARP.PARM_VIEW4;
        BPCOBPRP.DATA.PARM_VIEW5 = BPRPARP.PARM_VIEW5;
        BPCOBPRP.DATA.PARM_VIEW6 = BPRPARP.PARM_VIEW6;
        BPCOBPRP.DATA.PARM_VIEW7 = BPRPARP.PARM_VIEW7;
        BPCOBPRP.DATA.PARM_VIEW8 = BPRPARP.PARM_VIEW8;
        BPCOBPRP.DATA.PARM_VIEW9 = BPRPARP.PARM_VIEW9;
        BPCOBPRP.DATA.TXN_ID = BPRPARP.TXN_ID;
        BPCOBPRP.DATA.INQ_TXN = BPRPARP.INQ_TXN;
        BPCOBPRP.DATA.NXT_TXN = BPRPARP.NXT_TXN;
        BPCOBPRP.DATA.COPYBOOK = BPRPARP.COPYBOOK;
        BPCOBPRP.DATA.CHK_CPNT = BPRPARP.CHK_CPNT;
        BPCOBPRP.DATA.LEN = BPRPARP.LEN;
        BPCOBPRP.DATA.STSW = BPRPARP.STSW;
    }
    public void R000_WRITE_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 228;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOBPRP);
        SCCMPAG.DATA_LEN = 228;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRBPRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MAIN-BRO-PARP", BPCRBPRP);
        CEP.TRC(SCCGWA, BPCRBPRP.RC);
        CEP.TRC(SCCGWA, BPCRBPRP.RETURN_INFO);
        if (BPCRBPRP.RETURN_INFO == 'N' 
            && BPCRBPRP.FUNC != 'R') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_PARM_TYPE_NOTFND, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
