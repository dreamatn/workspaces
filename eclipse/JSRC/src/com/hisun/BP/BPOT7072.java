package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT7072 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    brParm BPTFHIS1_BR = new brParm();
    brParm BPTFHIS2_BR = new brParm();
    brParm BPTFHIST_BR = new brParm();
    DBParm BPTFHISA_RD;
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPW02";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_AGR_NO = " ";
    short WS_CNT = 0;
    String WS_TENOR = " ";
    double WS_CUR_BAL = 0;
    double WS_OUT_CUR_BAL = 0;
    short WS_D = 0;
    char WS_BROWS_COND = ' ';
    char WS_FRZ_FLG = ' ';
    char WS_BPTFHISA_FLG = ' ';
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    int WS_SET_DT = 0;
    int WS_SET_TM = 0;
    String WS_ACO_AC = " ";
    long WS_JRNNO = 0;
    short WS_JRN_SEQ = 0;
    int WS_TX_BR = 0;
    String WS_TX_TLR = " ";
    int WS_AC_SEQ = 0;
    String WS_AC_NO = " ";
    String WS_CI_NO = " ";
    String WS_TX_MMO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCF7072 BPCF7072 = new BPCF7072();
    BPRFHIS BPRFHIS = new BPRFHIS();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCQFHIS BPCQFHIS = new BPCQFHIS();
    CICQACAC CICQACAC = new CICQACAC();
    CICQCIAC CICQCIAC = new CICQCIAC();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    BPRFHISA BPRFHISA = new BPRFHISA();
    SCCGWA SCCGWA;
    BPB7072_AWA_7072 BPB7072_AWA_7072;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT7072 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7072_AWA_7072>");
        BPB7072_AWA_7072 = (BPB7072_AWA_7072) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB7072_AWA_7072.STR_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7072_AWA_7072.END_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7072_AWA_7072.STR_DT > BPB7072_AWA_7072.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB7072_AWA_7072.STR_DT);
        CEP.TRC(SCCGWA, BPB7072_AWA_7072.END_DT);
        if (BPB7072_AWA_7072.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7072_AWA_7072.CI_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CI WAY");
            WS_BROWS_COND = '1';
        } else if (BPB7072_AWA_7072.AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "AC WAY");
            WS_BROWS_COND = '2';
        }
        CEP.TRC(SCCGWA, WS_BROWS_COND);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if ((BPB7072_AWA_7072.STR_DT == SCCGWA.COMM_AREA.AC_DATE) 
            && (BPB7072_AWA_7072.END_DT == SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.TRC(SCCGWA, "READ DAY TBALE ONLY");
            B040_INTR_INFO1();
            if (pgmRtn) return;
        } else {
            if ((BPB7072_AWA_7072.END_DT == SCCGWA.COMM_AREA.AC_DATE) 
                && (BPB7072_AWA_7072.STR_DT < SCCGWA.COMM_AREA.AC_DATE)) {
                CEP.TRC(SCCGWA, "READ TWO TABLES");
                B040_INTR_INFO2();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "READ HIST TABLE ONLY");
                B040_INTR_INFO3();
                if (pgmRtn) return;
            }
        }
        B060_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        IBS.init(SCCGWA, BPCF7072);
        B050_DATA_TRANS();
        if (pgmRtn) return;
        WS_I = 1;
        if (WS_BROWS_COND == '2') {
            CEP.TRC(SCCGWA, "STR BY AC");
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB7072_AWA_7072.AC;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0) {
                WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            } else {
                if (CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO.trim().length() > 0) {
                    WS_AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.DATA.AGR_NO = WS_AGR_NO;
                    CICQACAC.FUNC = 'R';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                }
            }
            B050_01_BROWSE_BPTFHIS_BY_ACOAC();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '1') {
            CEP.TRC(SCCGWA, "STR BY CI");
            IBS.init(SCCGWA, CICQCIAC);
            CICQCIAC.DATA.CI_NO = BPB7072_AWA_7072.CI_NO;
            CICQCIAC.FUNC = '2';
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            for (WS_CNT = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO.trim().length() != 0 
                && WS_CNT <= 100 
                && WS_I <= 15; WS_CNT += 1) {
                WS_ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
                B050_01_BROWSE_BPTFHIS_BY_ACOAC();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_01_BROWSE_BPTFHIS_BY_ACOAC() throws IOException,SQLException,Exception {
        if (BPB7072_AWA_7072.STR_POS == 0) {
            T000_STARTBR_ACOAC();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_ACOAC_NEW();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 15) {
            if (SCRCWA.JRN_IN_USE == '1') {
                B060_01_DATA_TRANS_TO_FMT1();
                if (pgmRtn) return;
            } else {
                B060_01_DATA_TRANS_TO_FMT2();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCF7072.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPCF7072);
        B050_DATA_TRANS();
        if (pgmRtn) return;
        WS_I = 1;
        if (WS_BROWS_COND == '2') {
            CEP.TRC(SCCGWA, "STR BY AC");
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB7072_AWA_7072.AC;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0) {
                WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            } else {
                if (CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO.trim().length() > 0) {
                    WS_AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.DATA.AGR_NO = WS_AGR_NO;
                    CICQACAC.FUNC = 'R';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                }
            }
            B050_03_BRW_FHIST_BY_ACOAC();
            if (pgmRtn) return;
            B050_03_BRW_FHIS1_BY_ACOAC();
            if (pgmRtn) return;
            B050_03_BRW_FHIS2_BY_ACOAC();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '1') {
            CEP.TRC(SCCGWA, "STR BY CI");
            IBS.init(SCCGWA, CICQCIAC);
            CICQCIAC.DATA.CI_NO = BPB7072_AWA_7072.CI_NO;
            CICQCIAC.FUNC = '2';
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            for (WS_CNT = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO.trim().length() != 0 
                && WS_CNT <= 100 
                && WS_I <= 15; WS_CNT += 1) {
                WS_ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
                B050_03_BRW_FHIST_BY_ACOAC();
                if (pgmRtn) return;
            }
            for (WS_CNT = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO.trim().length() != 0 
                && WS_CNT <= 100 
                && WS_I <= 15; WS_CNT += 1) {
                WS_ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
                B050_03_BRW_FHIS1_BY_ACOAC();
                if (pgmRtn) return;
            }
            for (WS_CNT = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO.trim().length() != 0 
                && WS_CNT <= 100 
                && WS_I <= 15; WS_CNT += 1) {
                WS_ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
                B050_03_BRW_FHIS2_BY_ACOAC();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_03_BRW_FHIST_BY_ACOAC() throws IOException,SQLException,Exception {
        if (BPB7072_AWA_7072.STR_POS == 0) {
            T000_STARTBR_ACOAC3();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_ACOAC3_NEW();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 15) {
            B060_01_DATA_TRANS_TO_FMT3();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCF7072.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B050_03_BRW_FHIS1_BY_ACOAC() throws IOException,SQLException,Exception {
        if (BPB7072_AWA_7072.STR_POS == 0) {
            T000_STARTBR_ACOAC1();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_ACOAC1_NEW();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFHIS1();
        if (pgmRtn) return;
        WS_D = 0;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 15) {
            B060_01_DATA_TRANS_TO_FMT1();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS1();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCF7072.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIS1();
        if (pgmRtn) return;
    }
    public void B050_03_BRW_FHIS2_BY_ACOAC() throws IOException,SQLException,Exception {
        if (BPB7072_AWA_7072.STR_POS == 0) {
            T000_STARTBR_ACOAC2();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_ACOAC2_NEW();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFHIS2();
        if (pgmRtn) return;
        WS_D = 0;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 15) {
            B060_01_DATA_TRANS_TO_FMT2();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS2();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCF7072.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIS2();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPRFHIS);
        IBS.init(SCCGWA, BPCF7072);
        B050_DATA_TRANS();
        if (pgmRtn) return;
        WS_I = 1;
        if (WS_BROWS_COND == '2') {
            CEP.TRC(SCCGWA, "STR BY AC");
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB7072_AWA_7072.AC;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0) {
                WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            } else {
                if (CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO.trim().length() > 0) {
                    WS_AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.DATA.AGR_NO = WS_AGR_NO;
                    CICQACAC.FUNC = 'R';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                }
            }
            B050_02_BRW_FHIST_BY_ACOAC();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '1') {
            CEP.TRC(SCCGWA, "STR BY CI");
            IBS.init(SCCGWA, CICQCIAC);
            CICQCIAC.DATA.CI_NO = BPB7072_AWA_7072.CI_NO;
            CICQCIAC.FUNC = '2';
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            for (WS_CNT = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO.trim().length() != 0 
                && WS_CNT <= 100 
                && WS_I <= 15; WS_CNT += 1) {
                WS_ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
                B050_02_BRW_FHIST_BY_ACOAC();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_02_BRW_FHIST_BY_ACOAC() throws IOException,SQLException,Exception {
        if (BPB7072_AWA_7072.STR_POS == 0) {
            T000_STARTBR_ACOAC3();
            if (pgmRtn) return;
            B080_READ_NEXT_RECORD3();
            if (pgmRtn) return;
            B090_READ_NEXT_TABLE1();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = "" + BPB7072_AWA_7072.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 8).compareTo(SCCGWA.COMM_AREA.AC_DATE+"") < 0) {
                T000_STARTBR_ACOAC3_NEW();
                if (pgmRtn) return;
                B080_READ_NEXT_RECORD3();
                if (pgmRtn) return;
                B090_READ_NEXT_TABLE1();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_ACOAC_NEW();
                if (pgmRtn) return;
                B080_READ_NEXT_RECORD3();
                if (pgmRtn) return;
            }
        }
        if (WS_FRZ_FLG == 'N') {
            BPCF7072.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B080_READ_NEXT_RECORD1() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 15) {
            if (SCRCWA.JRN_IN_USE == '1') {
                B060_01_DATA_TRANS_TO_FMT1();
                if (pgmRtn) return;
            } else {
                B060_01_DATA_TRANS_TO_FMT2();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCF7072.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B080_READ_NEXT_RECORD3() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BBB");
        T000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        WS_I = 1;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 15) {
            B060_01_DATA_TRANS_TO_FMT3();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCF7072.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B090_READ_NEXT_TABLE1() throws IOException,SQLException,Exception {
        if (WS_I < 15) {
            IBS.init(SCCGWA, BPRFHIS);
            B050_DATA_TRANS();
            if (pgmRtn) return;
            T000_STARTBR_ACOAC();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
            while (WS_FRZ_FLG != 'N' 
                && SCCMPAG.FUNC != 'E' 
                && WS_I <= 15) {
                if (SCRCWA.JRN_IN_USE == '1') {
                    B060_01_DATA_TRANS_TO_FMT1();
                    if (pgmRtn) return;
                } else {
                    B060_01_DATA_TRANS_TO_FMT2();
                    if (pgmRtn) return;
                }
                T000_READNEXT_BPTFHIS();
                if (pgmRtn) return;
            }
            T000_ENDBR_BPTFHIS();
            if (pgmRtn) return;
        }
    }
    public void B050_DATA_TRANS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        WS_AC_NO = BPB7072_AWA_7072.AC;
        WS_STR_DT = BPB7072_AWA_7072.STR_DT;
        WS_END_DT = BPB7072_AWA_7072.END_DT;
        WS_CI_NO = BPB7072_AWA_7072.CI_NO;
        WS_TX_MMO = BPB7072_AWA_7072.TX_MMO;
        CEP.TRC(SCCGWA, BPB7072_AWA_7072.STR_POS);
        if (BPB7072_AWA_7072.STR_POS != 0) {
            JIBS_tmp_str[0] = "" + BPB7072_AWA_7072.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 8).trim().length() == 0) WS_STR_DT = 0;
            else WS_STR_DT = Integer.parseInt(JIBS_tmp_str[0].substring(0, 8));
            JIBS_tmp_str[0] = "" + BPB7072_AWA_7072.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1).trim().length() == 0) WS_JRNNO = 0;
            else WS_JRNNO = Long.parseLong(JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1));
            JIBS_tmp_str[0] = "" + BPB7072_AWA_7072.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1).trim().length() == 0) WS_JRN_SEQ = 0;
            else WS_JRN_SEQ = Short.parseShort(JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1));
        }
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCF7072;
        SCCFMT.DATA_LEN = 5099;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_01_DATA_TRANS_TO_FMT1() throws IOException,SQLException,Exception {
        if (WS_TX_MMO.trim().length() == 0 
            || (WS_TX_MMO.trim().length() > 0 
            && BPRFHIS1.TX_MMO.equalsIgnoreCase(WS_TX_MMO))) {
            BPCF7072.TIMES[WS_I-1].AC_DT = BPRFHIS1.KEY.AC_DT;
            BPCF7072.TIMES[WS_I-1].JRNNO = BPRFHIS1.KEY.JRNNO;
            BPCF7072.TIMES[WS_I-1].JRN_SEQ = BPRFHIS1.KEY.JRN_SEQ;
            BPCF7072.TIMES[WS_I-1].TX_BR = BPRFHIS1.TX_BR;
            BPCF7072.TIMES[WS_I-1].TX_DT = BPRFHIS1.TX_DT;
            BPCF7072.TIMES[WS_I-1].TX_TM = BPRFHIS1.TX_TM;
            BPCF7072.TIMES[WS_I-1].TX_CD = BPRFHIS1.TX_CD;
            BPCF7072.TIMES[WS_I-1].DRCR_FLG = BPRFHIS1.DRCRFLG;
            BPCF7072.TIMES[WS_I-1].PROD_CD = BPRFHIS1.PROD_CD;
            BPCF7072.TIMES[WS_I-1].PRDMO_CD = BPRFHIS1.PRDMO_CD;
            BPCF7072.TIMES[WS_I-1].BV_CODE = BPRFHIS1.BV_CODE;
            BPCF7072.TIMES[WS_I-1].HEAD_NO = BPRFHIS1.HEAD_NO;
            BPCF7072.TIMES[WS_I-1].BV_NO = BPRFHIS1.BV_NO;
            BPCF7072.TIMES[WS_I-1].CI_NO = BPRFHIS1.CI_NO;
            BPCF7072.TIMES[WS_I-1].ACO_AC = BPRFHIS1.ACO_AC;
            BPCF7072.TIMES[WS_I-1].TX_TOOL = BPRFHIS1.KEY.AC;
            BPCF7072.TIMES[WS_I-1].TX_CCY = BPRFHIS1.TX_CCY;
            BPCF7072.TIMES[WS_I-1].CCY_TYP = BPRFHIS1.TX_CCY_TYPE;
            BPCF7072.TIMES[WS_I-1].TX_TYPE = BPRFHIS1.TX_TYPE;
            BPCF7072.TIMES[WS_I-1].TX_AMT = BPRFHIS1.TX_AMT;
            BPCF7072.TIMES[WS_I-1].CUR_BAL = WS_OUT_CUR_BAL;
            BPCF7072.TIMES[WS_I-1].TX_MMO = BPRFHIS1.TX_MMO;
            BPCF7072.TIMES[WS_I-1].TX_STS = BPRFHIS1.TX_STS;
            BPCF7072.TIMES[WS_I-1].NARRATIVE = BPRFHIS1.NARRATIVE;
            WS_I = (short) (WS_I + 1);
            BPCF7072.TOTAL_NUM = (short) (BPCF7072.TOTAL_NUM + 1);
            JIBS_tmp_str[0] = "" + BPCF7072.END_POS;
            JIBS_f0 = "";
            for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCF7072.END_POS;
            JIBS_tmp_str[1] = "" + BPRFHIS1.KEY.AC_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(8);
            BPCF7072.END_POS = Long.parseLong(JIBS_NumStr);
            JIBS_tmp_str[0] = "" + BPCF7072.END_POS;
            JIBS_f0 = "";
            for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCF7072.END_POS;
            JIBS_tmp_str[1] = "" + BPRFHIS1.KEY.JRNNO;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 9 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(9 + 12 - 1);
            BPCF7072.END_POS = Long.parseLong(JIBS_NumStr);
            JIBS_tmp_str[0] = "" + BPCF7072.END_POS;
            JIBS_f0 = "";
            for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCF7072.END_POS;
            JIBS_tmp_str[1] = "" + BPRFHIS1.KEY.JRN_SEQ;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 21 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(21 + 4 - 1);
            BPCF7072.END_POS = Long.parseLong(JIBS_NumStr);
        }
    }
    public void B060_01_DATA_TRANS_TO_FMT2() throws IOException,SQLException,Exception {
        if (WS_TX_MMO.trim().length() == 0 
            || (WS_TX_MMO.trim().length() > 0 
            && BPRFHIS2.TX_MMO.equalsIgnoreCase(WS_TX_MMO))) {
            BPCF7072.TIMES[WS_I-1].AC_DT = BPRFHIS2.KEY.AC_DT;
            BPCF7072.TIMES[WS_I-1].JRNNO = BPRFHIS2.KEY.JRNNO;
            BPCF7072.TIMES[WS_I-1].JRN_SEQ = BPRFHIS2.KEY.JRN_SEQ;
            BPCF7072.TIMES[WS_I-1].TX_BR = BPRFHIS2.TX_BR;
            BPCF7072.TIMES[WS_I-1].TX_DT = BPRFHIS2.TX_DT;
            BPCF7072.TIMES[WS_I-1].TX_TM = BPRFHIS2.TX_TM;
            BPCF7072.TIMES[WS_I-1].TX_CD = BPRFHIS2.TX_CD;
            BPCF7072.TIMES[WS_I-1].DRCR_FLG = BPRFHIS2.DRCRFLG;
            BPCF7072.TIMES[WS_I-1].PROD_CD = BPRFHIS2.PROD_CD;
            BPCF7072.TIMES[WS_I-1].PRDMO_CD = BPRFHIS2.PRDMO_CD;
            BPCF7072.TIMES[WS_I-1].BV_CODE = BPRFHIS2.BV_CODE;
            BPCF7072.TIMES[WS_I-1].HEAD_NO = BPRFHIS2.HEAD_NO;
            BPCF7072.TIMES[WS_I-1].BV_NO = BPRFHIS2.BV_NO;
            BPCF7072.TIMES[WS_I-1].CI_NO = BPRFHIS2.CI_NO;
            BPCF7072.TIMES[WS_I-1].ACO_AC = BPRFHIS2.ACO_AC;
            BPCF7072.TIMES[WS_I-1].TX_TOOL = BPRFHIS2.KEY.AC;
            BPCF7072.TIMES[WS_I-1].TX_CCY = BPRFHIS2.TX_CCY;
            BPCF7072.TIMES[WS_I-1].CCY_TYP = BPRFHIS2.TX_CCY_TYPE;
            BPCF7072.TIMES[WS_I-1].TX_TYPE = BPRFHIS2.TX_TYPE;
            BPCF7072.TIMES[WS_I-1].TX_AMT = BPRFHIS2.TX_AMT;
            BPCF7072.TIMES[WS_I-1].CUR_BAL = WS_OUT_CUR_BAL;
            BPCF7072.TIMES[WS_I-1].TX_MMO = BPRFHIS2.TX_MMO;
            BPCF7072.TIMES[WS_I-1].TX_STS = BPRFHIS2.TX_STS;
            BPCF7072.TIMES[WS_I-1].NARRATIVE = BPRFHIS2.NARRATIVE;
            WS_I = (short) (WS_I + 1);
            BPCF7072.TOTAL_NUM = (short) (BPCF7072.TOTAL_NUM + 1);
            JIBS_tmp_str[0] = "" + BPCF7072.END_POS;
            JIBS_f0 = "";
            for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCF7072.END_POS;
            JIBS_tmp_str[1] = "" + BPRFHIS2.KEY.AC_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(8);
            BPCF7072.END_POS = Long.parseLong(JIBS_NumStr);
            JIBS_tmp_str[0] = "" + BPCF7072.END_POS;
            JIBS_f0 = "";
            for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCF7072.END_POS;
            JIBS_tmp_str[1] = "" + BPRFHIS2.KEY.JRNNO;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 9 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(9 + 12 - 1);
            BPCF7072.END_POS = Long.parseLong(JIBS_NumStr);
            JIBS_tmp_str[0] = "" + BPCF7072.END_POS;
            JIBS_f0 = "";
            for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCF7072.END_POS;
            JIBS_tmp_str[1] = "" + BPRFHIS2.KEY.JRN_SEQ;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 21 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(21 + 4 - 1);
            BPCF7072.END_POS = Long.parseLong(JIBS_NumStr);
        }
    }
    public void B060_01_DATA_TRANS_TO_FMT3() throws IOException,SQLException,Exception {
        if (WS_TX_MMO.trim().length() == 0 
            || (WS_TX_MMO.trim().length() > 0 
            && BPRFHIST.TX_MMO.equalsIgnoreCase(WS_TX_MMO))) {
            BPCF7072.TIMES[WS_I-1].AC_DT = BPRFHIST.KEY.AC_DT;
            BPCF7072.TIMES[WS_I-1].JRNNO = BPRFHIST.KEY.JRNNO;
            BPCF7072.TIMES[WS_I-1].JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
            BPCF7072.TIMES[WS_I-1].TX_BR = BPRFHIST.TX_BR;
            BPCF7072.TIMES[WS_I-1].TX_DT = BPRFHIST.TX_DT;
            BPCF7072.TIMES[WS_I-1].TX_TM = BPRFHIST.TX_TM;
            BPCF7072.TIMES[WS_I-1].TX_CD = BPRFHIST.TX_CD;
            BPCF7072.TIMES[WS_I-1].DRCR_FLG = BPRFHIST.DRCRFLG;
            BPCF7072.TIMES[WS_I-1].PROD_CD = BPRFHIST.PROD_CD;
            BPCF7072.TIMES[WS_I-1].PRDMO_CD = BPRFHIST.PRDMO_CD;
            BPCF7072.TIMES[WS_I-1].BV_CODE = BPRFHIST.BV_CODE;
            BPCF7072.TIMES[WS_I-1].HEAD_NO = BPRFHIST.HEAD_NO;
            BPCF7072.TIMES[WS_I-1].BV_NO = BPRFHIST.BV_NO;
            BPCF7072.TIMES[WS_I-1].CI_NO = BPRFHIST.CI_NO;
            BPCF7072.TIMES[WS_I-1].ACO_AC = BPRFHIST.ACO_AC;
            BPCF7072.TIMES[WS_I-1].TX_TOOL = BPRFHIST.KEY.AC;
            BPCF7072.TIMES[WS_I-1].TX_CCY = BPRFHIST.TX_CCY;
            BPCF7072.TIMES[WS_I-1].CCY_TYP = BPRFHIST.TX_CCY_TYPE;
            BPCF7072.TIMES[WS_I-1].TX_TYPE = BPRFHIST.TX_TYPE;
            BPCF7072.TIMES[WS_I-1].TX_AMT = BPRFHIST.TX_AMT;
            BPCF7072.TIMES[WS_I-1].CUR_BAL = BPRFHIST.CURR_BAL;
            BPCF7072.TIMES[WS_I-1].TX_MMO = BPRFHIST.TX_MMO;
            BPCF7072.TIMES[WS_I-1].TX_STS = BPRFHIST.TX_STS;
            BPCF7072.TIMES[WS_I-1].NARRATIVE = BPRFHIST.NARRATIVE;
            WS_I = (short) (WS_I + 1);
            BPCF7072.TOTAL_NUM = (short) (BPCF7072.TOTAL_NUM + 1);
            JIBS_tmp_str[0] = "" + BPCF7072.END_POS;
            JIBS_f0 = "";
            for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCF7072.END_POS;
            JIBS_tmp_str[1] = "" + BPRFHIST.KEY.AC_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(8);
            BPCF7072.END_POS = Long.parseLong(JIBS_NumStr);
            JIBS_tmp_str[0] = "" + BPCF7072.END_POS;
            JIBS_f0 = "";
            for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCF7072.END_POS;
            JIBS_tmp_str[1] = "" + BPRFHIST.KEY.JRNNO;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 9 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(9 + 12 - 1);
            BPCF7072.END_POS = Long.parseLong(JIBS_NumStr);
            JIBS_tmp_str[0] = "" + BPCF7072.END_POS;
            JIBS_f0 = "";
            for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCF7072.END_POS;
            JIBS_tmp_str[1] = "" + BPRFHIST.KEY.JRN_SEQ;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 21 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(21 + 4 - 1);
            BPCF7072.END_POS = Long.parseLong(JIBS_NumStr);
        }
    }
    public void R000_GET_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUIBAL);
        if (SCRCWA.JRN_IN_USE == '1') {
            BPCUIBAL.INPUT.AC_DT = BPRFHIS1.KEY.AC_DT;
            BPCUIBAL.INPUT.JRNNO = BPRFHIS1.KEY.JRNNO;
            BPCUIBAL.INPUT.JRN_SEQ = BPRFHIS1.KEY.JRN_SEQ;
            BPCUIBAL.INPUT.AC_IN = BPRFHIS1.ACO_AC;
            BPCUIBAL.INPUT.CCY = BPRFHIS1.TX_CCY;
            BPCUIBAL.INPUT.CCY_TYP = BPRFHIS1.TX_CCY_TYPE;
            BPCUIBAL.INPUT.TX_AMT_IN = BPRFHIS1.TX_AMT;
            BPCUIBAL.INPUT.TX_DRCRFLG = BPRFHIS1.DRCRFLG;
            BPCUIBAL.INPUT.INP_AC_TYP = '1';
            if (BPRFHIS1.SUMUP_FLG != '5') {
                S000_CALL_BPZUIBAL();
                if (pgmRtn) return;
            }
            BPRFHIS1.CURR_BAL = BPCUIBAL.OUTPUT.BAL_E;
            CEP.TRC(SCCGWA, BPRFHIST.CURR_BAL);
        } else {
            BPCUIBAL.INPUT.AC_DT = BPRFHIS2.KEY.AC_DT;
            BPCUIBAL.INPUT.JRNNO = BPRFHIS2.KEY.JRNNO;
            BPCUIBAL.INPUT.JRN_SEQ = BPRFHIS2.KEY.JRN_SEQ;
            BPCUIBAL.INPUT.AC_IN = BPRFHIS2.ACO_AC;
            BPCUIBAL.INPUT.CCY = BPRFHIS2.TX_CCY;
            BPCUIBAL.INPUT.CCY_TYP = BPRFHIS2.TX_CCY_TYPE;
            BPCUIBAL.INPUT.TX_AMT_IN = BPRFHIS2.TX_AMT;
            BPCUIBAL.INPUT.TX_DRCRFLG = BPRFHIS2.DRCRFLG;
            BPCUIBAL.INPUT.INP_AC_TYP = '1';
            if (BPRFHIS2.SUMUP_FLG != '5') {
                S000_CALL_BPZUIBAL();
                if (pgmRtn) return;
            }
            BPRFHIS2.CURR_BAL = BPCUIBAL.OUTPUT.BAL_E;
            CEP.TRC(SCCGWA, BPRFHIST.CURR_BAL);
        }
    }
    public void R000_GET_AC_BAL1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHISA);
        BPRFHISA.AC_DT = BPRFHIS1.KEY.AC_DT;
        BPRFHISA.KEY.AC = BPRFHIS1.ACO_AC;
        BPRFHISA.KEY.CCY = BPRFHIS1.TX_CCY;
        BPRFHISA.KEY.CCY_TYP = BPRFHIS1.TX_CCY_TYPE;
        BPRFHISA.KEY.PART_NO = BPRFHIS1.KEY.PART_NO;
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY);
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY_TYP);
        T000_READ_BPTFHISA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
        if (WS_BPTFHISA_FLG == 'Y') {
            WS_CUR_BAL = BPRFHISA.CUR_BAL;
        }
        if (WS_BPTFHISA_FLG == 'N') {
            WS_CUR_BAL = 0;
        }
    }
    public void R000_GET_AC_BAL2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHISA);
        BPRFHISA.AC_DT = BPRFHIS2.KEY.AC_DT;
        BPRFHISA.KEY.AC = BPRFHIS2.ACO_AC;
        BPRFHISA.KEY.CCY = BPRFHIS2.TX_CCY;
        BPRFHISA.KEY.CCY_TYP = BPRFHIS2.TX_CCY_TYPE;
        BPRFHISA.KEY.PART_NO = BPRFHIS2.KEY.PART_NO;
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY);
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY_TYP);
        T000_READ_BPTFHISA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
        if (WS_BPTFHISA_FLG == 'Y') {
            WS_CUR_BAL = BPRFHISA.CUR_BAL;
        }
        if (WS_BPTFHISA_FLG == 'N') {
            WS_CUR_BAL = 0;
        }
    }
    public void S000_CALL_BPZUIBAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-U-INQ-BAL";
        SCCCALL.COMMAREA_PTR = BPCUIBAL;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, BPCUIBAL.RC);
    }
    public void T000_STARTBR_ACOAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            CEP.TRC(SCCGWA, "READ TABLE1");
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "AC_DT = :WS_STR_DT "
                + "AND ACO_AC = :WS_ACO_AC";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            CEP.TRC(SCCGWA, "READ TABLE2");
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "AC_DT = :WS_STR_DT "
                + "AND ACO_AC = :WS_ACO_AC";
            BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            CEP.TRC(SCCGWA, "READ TABLE1");
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "AC_DT = :WS_STR_DT "
                + "AND ACO_AC = :WS_ACO_AC "
                + "AND ( ( JRNNO = :WS_JRNNO "
                + "AND JRN_SEQ > :WS_JRN_SEQ ) "
                + "OR JRNNO > :WS_JRNNO )";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            CEP.TRC(SCCGWA, "READ TABLE2");
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "AC_DT = :WS_STR_DT "
                + "AND ACO_AC = :WS_ACO_AC "
                + "AND ( ( JRNNO = :WS_JRNNO "
                + "AND JRN_SEQ > :WS_JRN_SEQ ) "
                + "OR JRNNO > :WS_JRNNO )";
            BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ TABLE1");
        BPTFHIS1_BR.rp = new DBParm();
        BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
        BPTFHIS1_BR.rp.where = "( AC_DT >= :WS_STR_DT "
            + "AND AC_DT <= :WS_END_DT ) "
            + "AND ACO_AC = :WS_ACO_AC";
        BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS1";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC1_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ TABLE1");
        BPTFHIS1_BR.rp = new DBParm();
        BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
        BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
            + "AND AC_DT <= :WS_END_DT "
            + "AND ( ( ( ( JRNNO = :WS_JRNNO "
            + "AND JRN_SEQ > :WS_JRN_SEQ ) "
            + "OR JRNNO > :WS_JRNNO ) "
            + "AND AC_DT = :WS_STR_DT ) "
            + "OR AC_DT > :WS_STR_DT )";
        BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS1";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ TABLE1");
        BPTFHIS2_BR.rp = new DBParm();
        BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
        BPTFHIS2_BR.rp.where = "( AC_DT >= :WS_STR_DT "
            + "AND AC_DT <= :WS_END_DT ) "
            + "AND ACO_AC = :WS_ACO_AC";
        BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS2";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC2_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ TABLE2");
        BPTFHIS2_BR.rp = new DBParm();
        BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
        BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
            + "AND AC_DT <= :WS_END_DT "
            + "AND ( ( ( ( JRNNO = :WS_JRNNO "
            + "AND JRN_SEQ > :WS_JRN_SEQ ) "
            + "OR JRNNO > :WS_JRNNO ) "
            + "AND AC_DT = :WS_STR_DT ) "
            + "OR AC_DT > :WS_STR_DT )";
        BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS2";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC3() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ TABLE1");
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "( AC_DT >= :WS_STR_DT "
            + "AND AC_DT <= :WS_END_DT ) "
            + "AND ACO_AC = :WS_ACO_AC";
        BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC3_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ TABLE1");
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
            + "AND AC_DT <= :WS_END_DT "
            + "AND ( ( ( ( JRNNO = :WS_JRNNO "
            + "AND JRN_SEQ > :WS_JRN_SEQ ) "
            + "OR JRNNO > :WS_JRNNO ) "
            + "AND AC_DT = :WS_STR_DT ) "
            + "OR AC_DT > :WS_STR_DT )";
        BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            IBS.READNEXT(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            IBS.READNEXT(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            R000_GET_AC_BAL();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFHIS1() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.trim().length() == 0) {
                CICQACAC.DATA.ACAC_NO = BPRFHIS1.ACO_AC;
                CICQACAC.FUNC = 'A';
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
            }
            if (WS_D == 0) {
                R000_GET_AC_BAL1();
                if (pgmRtn) return;
            }
            if (BPRFHIS1.DRCRFLG == 'C') {
                WS_CUR_BAL = WS_CUR_BAL + BPRFHIS1.TX_AMT;
            } else {
                WS_CUR_BAL = WS_CUR_BAL - BPRFHIS1.TX_AMT;
            }
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("IB") 
                || CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("LN")) {
                WS_OUT_CUR_BAL = WS_CUR_BAL * ( -1 );
            } else {
                WS_OUT_CUR_BAL = WS_CUR_BAL;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS1";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFHIS2() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.trim().length() == 0) {
                CICQACAC.DATA.ACAC_NO = BPRFHIS2.ACO_AC;
                CICQACAC.FUNC = 'A';
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
            }
            if (WS_D == 0) {
                R000_GET_AC_BAL2();
                if (pgmRtn) return;
            }
            if (BPRFHIS2.DRCRFLG == 'C') {
                WS_CUR_BAL = WS_CUR_BAL + BPRFHIS2.TX_AMT;
            } else {
                WS_CUR_BAL = WS_CUR_BAL - BPRFHIS2.TX_AMT;
            }
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("IB") 
                || CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("LN")) {
                WS_OUT_CUR_BAL = WS_CUR_BAL * ( -1 );
            } else {
                WS_OUT_CUR_BAL = WS_CUR_BAL;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS2";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTFHIS1() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIS1_BR);
    }
    public void T000_ENDBR_BPTFHIS2() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIS2_BR);
    }
    public void T000_ENDBR_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            IBS.ENDBR(SCCGWA, BPTFHIS1_BR);
        } else {
            IBS.ENDBR(SCCGWA, BPTFHIS2_BR);
        }
    }
    public void T000_ENDBR_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIST_BR);
    }
    public void T000_READ_BPTFHISA() throws IOException,SQLException,Exception {
        BPTFHISA_RD = new DBParm();
        BPTFHISA_RD.TableName = "BPTFHISA";
        BPTFHISA_RD.col = "AC_DT,CUR_BAL";
        BPTFHISA_RD.where = "AC = :BPRFHISA.KEY.AC "
            + "AND CCY = :BPRFHISA.KEY.CCY "
            + "AND CCY_TYP = :BPRFHISA.KEY.CCY_TYP "
            + "AND AC_DT < :BPRFHISA.AC_DT "
            + "AND PART_NO = :BPRFHISA.KEY.PART_NO";
        BPTFHISA_RD.fst = true;
        BPTFHISA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, BPRFHISA, this, BPTFHISA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHISA_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHISA_FLG = 'N';
        } else {
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "DC-INQ-AC-INF";
        SCCCALL.COMMAREA_PTR = DCCPACTY;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        CEP.TRC(SCCGWA, CICQCIAC.RC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQCIAC.RC);
        }
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
