package com.hisun.BP;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT7070 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    brParm BPTFHIS1_BR = new brParm();
    brParm BPTFHIS2_BR = new brParm();
    brParm BPTFHIST_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPA03";
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
    String WS_TENOR = " ";
    char WS_BROWS_COND = ' ';
    char WS_FRZ_FLG = ' ';
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
    double WS_TX_AMT_FROM = 0;
    double WS_TX_AMT_TO = 0;
    String WS_RLT_AC_NAME = " ";
    String WS_CI_NO = " ";
    char WS_STS = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCFHTTT BPCFHTTT = new BPCFHTTT();
    BPRFHIS BPRFHIS = new BPRFHIS();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCQFHIS BPCQFHIS = new BPCQFHIS();
    CICQACAC CICQACAC = new CICQACAC();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    BPB7070_AWA_7070 BPB7070_AWA_7070;
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
        CEP.TRC(SCCGWA, "BPOT7070 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7070_AWA_7070>");
        BPB7070_AWA_7070 = (BPB7070_AWA_7070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        if (BPB7070_AWA_7070.STR_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7070_AWA_7070.END_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7070_AWA_7070.STR_DT > BPB7070_AWA_7070.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB7070_AWA_7070.STR_DT);
        CEP.TRC(SCCGWA, BPB7070_AWA_7070.END_DT);
        if (BPB7070_AWA_7070.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB7070_AWA_7070.TX_AMT_F);
        CEP.TRC(SCCGWA, BPB7070_AWA_7070.TX_AMT_T);
        if ((BPB7070_AWA_7070.TX_AMT_F != 0 
            && BPB7070_AWA_7070.TX_AMT_T == 0) 
            || (BPB7070_AWA_7070.TX_AMT_F == 0 
            && BPB7070_AWA_7070.TX_AMT_T != 0)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7070_AWA_7070.TX_AMT_F > BPB7070_AWA_7070.TX_AMT_T) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRS_AMT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7070_AWA_7070.JRNNO == 0 
            && BPB7070_AWA_7070.AC.trim().length() == 0 
            && BPB7070_AWA_7070.TX_TOOL.trim().length() == 0 
            && BPB7070_AWA_7070.TX_TLR.trim().length() == 0 
            && BPB7070_AWA_7070.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INP_ONE_COND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7070_AWA_7070.JRNNO != 0 
                && BPB7070_AWA_7070.STR_DT != 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_JRNNO");
            WS_BROWS_COND = '1';
        } else if (BPB7070_AWA_7070.AC.trim().length() == 0 
                && BPB7070_AWA_7070.TX_TOOL.trim().length() == 0 
                && BPB7070_AWA_7070.TX_CD.trim().length() == 0 
                && BPB7070_AWA_7070.TX_BR != 0 
                && BPB7070_AWA_7070.TX_TLR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE BY BR TLR");
            WS_BROWS_COND = '2';
        } else if (((BPB7070_AWA_7070.AC.trim().length() > 0 
                || BPB7070_AWA_7070.CI_NO.trim().length() > 0) 
                && (BPB7070_AWA_7070.TX_AMT_F != 0 
                || BPB7070_AWA_7070.TX_AMT_T != 0 
                || BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0))) {
            WS_BROWS_COND = '4';
        } else {
            CEP.TRC(SCCGWA, "CI WAY");
            WS_BROWS_COND = '3';
        }
        CEP.TRC(SCCGWA, WS_BROWS_COND);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if ((BPB7070_AWA_7070.STR_DT == SCCGWA.COMM_AREA.AC_DATE) 
            && (BPB7070_AWA_7070.END_DT == SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.TRC(SCCGWA, "READ DAY TBALE ONLY");
            B040_INTR_INFO1();
            if (pgmRtn) return;
        } else {
            if ((BPB7070_AWA_7070.END_DT == SCCGWA.COMM_AREA.AC_DATE) 
                && (BPB7070_AWA_7070.STR_DT < SCCGWA.COMM_AREA.AC_DATE)) {
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
        IBS.init(SCCGWA, BPCFHTTT);
        B050_DATA_TRANS1();
        if (pgmRtn) return;
        if (BPB7070_AWA_7070.STR_POS == 0) {
            if (WS_BROWS_COND == '1') {
                CEP.TRC(SCCGWA, "JRNNO");
                T000_STARTBR_JRNNO();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '2') {
                CEP.TRC(SCCGWA, "BRTLR");
                T000_STARTBR_BRTLR();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '4') {
                CEP.TRC(SCCGWA, "ACCI-1");
                if (BPB7070_AWA_7070.AC.trim().length() > 0) {
                    B045_GET_ACOAC_FROM_CIZQACAC();
                    if (pgmRtn) return;
                    T000_STARTBR_ACNO();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, CICCUST);
                    CICCUST.FUNC = 'C';
                    CICCUST.DATA.CI_NO = BPB7070_AWA_7070.CI_NO;
                    CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                    T000_STARTBR_CINO();
                    if (pgmRtn) return;
                }
            } else if (WS_BROWS_COND == '3') {
                CEP.TRC(SCCGWA, "AC");
                B045_GET_ACOAC_FROM_CIZQACAC();
                if (pgmRtn) return;
                T000_STARTBR_ACOAC();
                if (pgmRtn) return;
            }
        } else {
            if (WS_BROWS_COND == '1') {
                T000_STARTBR_JRNNO_NEW();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '2') {
                T000_STARTBR_BRTLR_NEW();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '4') {
                CEP.TRC(SCCGWA, "ACCI-1");
                if (BPB7070_AWA_7070.AC.trim().length() > 0) {
                    B045_GET_ACOAC_FROM_CIZQACAC();
                    if (pgmRtn) return;
                    T000_STARTBR_ACNO();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, CICCUST);
                    CICCUST.FUNC = 'C';
                    CICCUST.DATA.CI_NO = BPB7070_AWA_7070.CI_NO;
                    CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                    T000_STARTBR_CINO();
                    if (pgmRtn) return;
                }
            } else if (WS_BROWS_COND == '3') {
                B045_GET_ACOAC_FROM_CIZQACAC();
                if (pgmRtn) return;
                T000_STARTBR_ACOAC_NEW();
                if (pgmRtn) return;
            }
        }
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        WS_I = 1;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 5) {
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
            BPCFHTTT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        IBS.init(SCCGWA, BPCFHTTT);
        B050_DATA_TRANS3();
        if (pgmRtn) return;
        if (BPB7070_AWA_7070.STR_POS == 0) {
            if (WS_BROWS_COND == '1') {
                T000_STARTBR_JRNNO3();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '2') {
                T000_STARTBR_BRTLR3();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '4') {
                CEP.TRC(SCCGWA, "ACCI-3");
                if (BPB7070_AWA_7070.AC.trim().length() > 0) {
                    B045_GET_ACOAC_FROM_CIZQACAC();
                    if (pgmRtn) return;
                    T000_STARTBR_ACNO3();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, CICCUST);
                    CICCUST.FUNC = 'C';
                    CICCUST.DATA.CI_NO = BPB7070_AWA_7070.CI_NO;
                    CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                    T000_STARTBR_CINO3();
                    if (pgmRtn) return;
                }
            } else if (WS_BROWS_COND == '3') {
                B045_GET_ACOAC_FROM_CIZQACAC();
                if (pgmRtn) return;
                T000_STARTBR_ACOAC3();
                if (pgmRtn) return;
            }
        } else {
            if (WS_BROWS_COND == '1') {
                T000_STARTBR_JRNNO_NEW3();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '2') {
                T000_STARTBR_BRTLR_NEW3();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '4') {
                CEP.TRC(SCCGWA, "ACCI-3");
                if (BPB7070_AWA_7070.AC.trim().length() > 0) {
                    B045_GET_ACOAC_FROM_CIZQACAC();
                    if (pgmRtn) return;
                    T000_STARTBR_ACNO3();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, CICCUST);
                    CICCUST.FUNC = 'C';
                    CICCUST.DATA.CI_NO = BPB7070_AWA_7070.CI_NO;
                    CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                    T000_STARTBR_CINO3();
                    if (pgmRtn) return;
                }
            } else if (WS_BROWS_COND == '3') {
                B045_GET_ACOAC_FROM_CIZQACAC();
                if (pgmRtn) return;
                T000_STARTBR_ACOAC_NEW3();
                if (pgmRtn) return;
            }
        }
        T000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        WS_I = 1;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 5) {
            B060_01_DATA_TRANS_TO_FMT3();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        IBS.init(SCCGWA, BPCFHTTT);
        B050_DATA_TRANS3();
        if (pgmRtn) return;
        if (BPB7070_AWA_7070.STR_POS == 0) {
            if (WS_BROWS_COND == '1') {
                T000_STARTBR_JRNNO3();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '2') {
                T000_STARTBR_BRTLR3();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '4') {
                CEP.TRC(SCCGWA, "ACCI-2");
                if (BPB7070_AWA_7070.AC.trim().length() > 0) {
                    B045_GET_ACOAC_FROM_CIZQACAC();
                    if (pgmRtn) return;
                    T000_STARTBR_ACNO3();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, CICCUST);
                    CICCUST.FUNC = 'C';
                    CICCUST.DATA.CI_NO = BPB7070_AWA_7070.CI_NO;
                    CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                    T000_STARTBR_CINO3();
                    if (pgmRtn) return;
                }
            } else if (WS_BROWS_COND == '3') {
                B045_GET_ACOAC_FROM_CIZQACAC();
                if (pgmRtn) return;
                T000_STARTBR_ACOAC3();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "WWW");
            }
            CEP.TRC(SCCGWA, "AAA");
            B080_READ_NEXT_RECORD3();
            if (pgmRtn) return;
            B090_READ_NEXT_TABLE1();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = "" + BPB7070_AWA_7070.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 8).compareTo(SCCGWA.COMM_AREA.AC_DATE+"") < 0) {
                if (WS_BROWS_COND == '1') {
                    T000_STARTBR_JRNNO_NEW();
                    if (pgmRtn) return;
                } else if (WS_BROWS_COND == '2') {
                    T000_STARTBR_BRTLR_NEW();
                    if (pgmRtn) return;
                } else if (WS_BROWS_COND == '4') {
                    CEP.TRC(SCCGWA, "ACCI-2");
                    if (BPB7070_AWA_7070.AC.trim().length() > 0) {
                        B045_GET_ACOAC_FROM_CIZQACAC();
                        if (pgmRtn) return;
                        T000_STARTBR_ACNO3();
                        if (pgmRtn) return;
                    } else {
                        IBS.init(SCCGWA, CICCUST);
                        CICCUST.FUNC = 'C';
                        CICCUST.DATA.CI_NO = BPB7070_AWA_7070.CI_NO;
                        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                        S000_CALL_CIZCUST();
                        if (pgmRtn) return;
                        T000_STARTBR_CINO3();
                        if (pgmRtn) return;
                    }
                } else if (WS_BROWS_COND == '3') {
                    B045_GET_ACOAC_FROM_CIZQACAC();
                    if (pgmRtn) return;
                    T000_STARTBR_ACOAC_NEW();
                    if (pgmRtn) return;
                }
                B080_READ_NEXT_RECORD3();
                if (pgmRtn) return;
                B090_READ_NEXT_TABLE1();
                if (pgmRtn) return;
            } else {
                if (WS_BROWS_COND == '1') {
                    T000_STARTBR_JRNNO_NEW();
                    if (pgmRtn) return;
                } else if (WS_BROWS_COND == '2') {
                    T000_STARTBR_BRTLR_NEW();
                    if (pgmRtn) return;
                } else if (WS_BROWS_COND == '4') {
                    CEP.TRC(SCCGWA, "ACCI-2");
                    if (BPB7070_AWA_7070.AC.trim().length() > 0) {
                        B045_GET_ACOAC_FROM_CIZQACAC();
                        if (pgmRtn) return;
                        T000_STARTBR_ACNO3();
                        if (pgmRtn) return;
                    } else {
                        IBS.init(SCCGWA, CICCUST);
                        CICCUST.FUNC = 'C';
                        CICCUST.DATA.CI_NO = BPB7070_AWA_7070.CI_NO;
                        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                        S000_CALL_CIZCUST();
                        if (pgmRtn) return;
                        T000_STARTBR_CINO3();
                        if (pgmRtn) return;
                    }
                } else if (WS_BROWS_COND == '3') {
                    B045_GET_ACOAC_FROM_CIZQACAC();
                    if (pgmRtn) return;
                    T000_STARTBR_ACOAC_NEW();
                    if (pgmRtn) return;
                }
                B080_READ_NEXT_RECORD1();
                if (pgmRtn) return;
            }
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
    }
    public void B045_GET_ACOAC_FROM_CIZQACAC() throws IOException,SQLException,Exception {
        B046_TRANS_DATA_CIZQACAC();
        if (pgmRtn) return;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, WS_ACO_AC);
    }
    public void B080_READ_NEXT_RECORD1() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        WS_I = 1;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_I <= 5) {
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
            BPCFHTTT.END_FLG = 'Y';
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
            && WS_I <= 5) {
            B060_01_DATA_TRANS_TO_FMT3();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B090_READ_NEXT_TABLE1() throws IOException,SQLException,Exception {
        if (WS_I < 5) {
            IBS.init(SCCGWA, BPRFHIS);
            IBS.init(SCCGWA, BPCFHTTT);
            B050_DATA_TRANS1();
            if (pgmRtn) return;
            WS_STR_DT = BPB7070_AWA_7070.END_DT;
            if (WS_BROWS_COND == '1') {
                T000_STARTBR_JRNNO();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '2') {
                T000_STARTBR_BRTLR();
                if (pgmRtn) return;
            } else if (WS_BROWS_COND == '4') {
                CEP.TRC(SCCGWA, "ACCI-21");
                if (BPB7070_AWA_7070.AC.trim().length() > 0) {
                    T000_STARTBR_ACNO();
                    if (pgmRtn) return;
                } else {
                    T000_STARTBR_CINO();
                    if (pgmRtn) return;
                }
            } else if (WS_BROWS_COND == '3') {
                B045_GET_ACOAC_FROM_CIZQACAC();
                if (pgmRtn) return;
                T000_STARTBR_ACOAC();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
            while (WS_FRZ_FLG != 'N' 
                && SCCMPAG.FUNC != 'E' 
                && WS_I <= 5) {
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
    public void B046_TRANS_DATA_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = BPB7070_AWA_7070.AC;
        CEP.TRC(SCCGWA, BPB7070_AWA_7070.AC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        if (BPB7070_AWA_7070.AC.trim().length() == 0) {
            CICQACAC.DATA.AGR_NO = BPB7070_AWA_7070.TX_TOOL;
        }
        CICQACAC.DATA.AGR_SEQ = BPB7070_AWA_7070.AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = BPB7070_AWA_7070.TX_CCY;
        CICQACAC.DATA.CR_FLG = BPB7070_AWA_7070.CCY_TYPE;
        CICQACAC.DATA.BV_NO = BPB7070_AWA_7070.BV_NO;
        CICQACAC.FUNC = 'R';
    }
    public void B050_DATA_TRANS1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        WS_JRNNO = BPB7070_AWA_7070.JRNNO;
        WS_JRN_SEQ = BPB7070_AWA_7070.JRN_SEQ;
        BPRFHIS.KEY.AC = BPB7070_AWA_7070.AC;
        BPRFHIS.REF_NO = BPB7070_AWA_7070.REF_NO;
        BPRFHIS.TX_TOOL = BPB7070_AWA_7070.TX_TOOL;
        WS_AC_SEQ = BPB7070_AWA_7070.AC_SEQ;
        BPRFHIS.TX_CCY = BPB7070_AWA_7070.TX_CCY;
        BPRFHIS.TX_CCY_TYPE = BPB7070_AWA_7070.CCY_TYPE;
        BPRFHIS.TX_CD = BPB7070_AWA_7070.TX_CD;
        WS_TX_TLR = BPB7070_AWA_7070.TX_TLR;
        WS_TX_BR = BPB7070_AWA_7070.TX_BR;
        WS_STR_DT = BPB7070_AWA_7070.STR_DT;
        WS_END_DT = BPB7070_AWA_7070.END_DT;
        BPRFHIS.CI_NO = BPB7070_AWA_7070.CI_NO;
        WS_CI_NO = BPB7070_AWA_7070.CI_NO;
        BPRFHIS.DRCRFLG = BPB7070_AWA_7070.DRCR_FLG;
        BPRFHIS.RLT_AC = BPB7070_AWA_7070.REL_AC;
        BPRFHIS.TX_STS = BPB7070_AWA_7070.STS;
        if (BPB7070_AWA_7070.STS != ' ') {
            WS_STS = BPB7070_AWA_7070.STS;
        } else {
            WS_STS = ALL.charAt(0);
        }
        BPRFHIS.BV_NO = BPB7070_AWA_7070.BV_NO;
        if (BPB7070_AWA_7070.STR_POS != 0) {
            JIBS_tmp_str[0] = "" + BPB7070_AWA_7070.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 8).trim().length() == 0) WS_STR_DT = 0;
            else WS_STR_DT = Integer.parseInt(JIBS_tmp_str[0].substring(0, 8));
            JIBS_tmp_str[0] = "" + BPB7070_AWA_7070.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1).trim().length() == 0) WS_JRNNO = 0;
            else WS_JRNNO = Long.parseLong(JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1));
            JIBS_tmp_str[0] = "" + BPB7070_AWA_7070.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1).trim().length() == 0) WS_JRN_SEQ = 0;
            else WS_JRN_SEQ = Short.parseShort(JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1));
        }
        WS_TX_AMT_FROM = BPB7070_AWA_7070.TX_AMT_F;
        WS_TX_AMT_TO = BPB7070_AWA_7070.TX_AMT_T;
        WS_RLT_AC_NAME = BPB7070_AWA_7070.RL_AC_NM;
    }
    public void B050_DATA_TRANS3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        WS_JRNNO = BPB7070_AWA_7070.JRNNO;
        WS_JRN_SEQ = BPB7070_AWA_7070.JRN_SEQ;
        BPRFHIST.KEY.AC = BPB7070_AWA_7070.AC;
        BPRFHIST.REF_NO = BPB7070_AWA_7070.REF_NO;
        BPRFHIST.TX_TOOL = BPB7070_AWA_7070.TX_TOOL;
        WS_AC_SEQ = BPB7070_AWA_7070.AC_SEQ;
        BPRFHIST.TX_CCY = BPB7070_AWA_7070.TX_CCY;
        BPRFHIST.TX_CCY_TYPE = BPB7070_AWA_7070.CCY_TYPE;
        BPRFHIST.TX_CD = BPB7070_AWA_7070.TX_CD;
        WS_TX_TLR = BPB7070_AWA_7070.TX_TLR;
        WS_TX_BR = BPB7070_AWA_7070.TX_BR;
        WS_STR_DT = BPB7070_AWA_7070.STR_DT;
        WS_END_DT = BPB7070_AWA_7070.END_DT;
        BPRFHIST.CI_NO = BPB7070_AWA_7070.CI_NO;
        WS_CI_NO = BPB7070_AWA_7070.CI_NO;
        BPRFHIST.DRCRFLG = BPB7070_AWA_7070.DRCR_FLG;
        BPRFHIST.RLT_AC = BPB7070_AWA_7070.REL_AC;
        BPRFHIST.TX_STS = BPB7070_AWA_7070.STS;
        if (BPB7070_AWA_7070.STS != ' ') {
            WS_STS = BPB7070_AWA_7070.STS;
        } else {
            WS_STS = ALL.charAt(0);
        }
        BPRFHIST.BV_NO = BPB7070_AWA_7070.BV_NO;
        if (BPB7070_AWA_7070.STR_POS != 0) {
            JIBS_tmp_str[0] = "" + BPB7070_AWA_7070.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 8).trim().length() == 0) WS_STR_DT = 0;
            else WS_STR_DT = Integer.parseInt(JIBS_tmp_str[0].substring(0, 8));
            JIBS_tmp_str[0] = "" + BPB7070_AWA_7070.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1).trim().length() == 0) WS_JRNNO = 0;
            else WS_JRNNO = Long.parseLong(JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1));
            JIBS_tmp_str[0] = "" + BPB7070_AWA_7070.STR_POS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1).trim().length() == 0) WS_JRN_SEQ = 0;
            else WS_JRN_SEQ = Short.parseShort(JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1));
        }
        WS_TX_AMT_FROM = BPB7070_AWA_7070.TX_AMT_F;
        WS_TX_AMT_TO = BPB7070_AWA_7070.TX_AMT_T;
        WS_RLT_AC_NAME = BPB7070_AWA_7070.RL_AC_NM;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCFHTTT;
        SCCFMT.DATA_LEN = 6628;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_01_DATA_TRANS_TO_FMT1() throws IOException,SQLException,Exception {
        BPCFHTTT.TIMES[WS_I-1].TX_CD = BPRFHIS1.TX_CD;
        BPCFHTTT.TIMES[WS_I-1].TX_MMO = BPRFHIS1.TX_MMO;
        BPCFHTTT.TIMES[WS_I-1].TX_MMO_NM = " ";
        BPCFHTTT.TIMES[WS_I-1].TX_DT = BPRFHIS1.TX_DT;
        BPCFHTTT.TIMES[WS_I-1].TX_TM = BPRFHIS1.TX_TM;
        BPCFHTTT.TIMES[WS_I-1].TX_SYS_JRN = BPRFHIS1.TX_SYS_JRN;
        BPCFHTTT.TIMES[WS_I-1].AC_DT = BPRFHIS1.KEY.AC_DT;
        BPCFHTTT.TIMES[WS_I-1].JRNNO = BPRFHIS1.KEY.JRNNO;
        BPCFHTTT.TIMES[WS_I-1].JRN_SEQ = BPRFHIS1.KEY.JRN_SEQ;
        BPCFHTTT.TIMES[WS_I-1].CI_NO = BPRFHIS1.CI_NO;
        BPCFHTTT.TIMES[WS_I-1].BV_CODE = BPRFHIS1.BV_CODE;
        BPCFHTTT.TIMES[WS_I-1].HEAD_NO = BPRFHIS1.HEAD_NO;
        BPCFHTTT.TIMES[WS_I-1].BV_NO = BPRFHIS1.BV_NO;
        BPCFHTTT.TIMES[WS_I-1].DRCR_FLG = BPRFHIS1.DRCRFLG;
        BPCFHTTT.TIMES[WS_I-1].AC = BPRFHIS1.KEY.AC;
        BPCFHTTT.TIMES[WS_I-1].ACO_AC = BPRFHIS1.ACO_AC;
        BPCFHTTT.TIMES[WS_I-1].TX_CCY = BPRFHIS1.TX_CCY;
        BPCFHTTT.TIMES[WS_I-1].CCY_TYP = BPRFHIS1.TX_CCY_TYPE;
        BPCFHTTT.TIMES[WS_I-1].AC_SEQ = BPB7070_AWA_7070.AC_SEQ;
        BPCFHTTT.TIMES[WS_I-1].TX_TYPE = BPRFHIS1.TX_TYPE;
        BPCFHTTT.TIMES[WS_I-1].TX_AMT = BPRFHIS1.TX_AMT;
        BPCFHTTT.TIMES[WS_I-1].CUR_BAL = BPRFHIS1.CURR_BAL;
        BPCFHTTT.TIMES[WS_I-1].PROD_CD = BPRFHIS1.PROD_CD;
        BPCFHTTT.TIMES[WS_I-1].REF_NO = BPRFHIS1.REF_NO;
        BPCFHTTT.TIMES[WS_I-1].TX_TOOL = BPRFHIS1.TX_TOOL;
        BPCFHTTT.TIMES[WS_I-1].APP_MMO = BPRFHIS1.APP_MMO;
        BPCFHTTT.TIMES[WS_I-1].TX_BR = BPRFHIS1.TX_BR;
        BPCFHTTT.TIMES[WS_I-1].TX_DP = BPRFHIS1.TX_DP;
        BPCFHTTT.TIMES[WS_I-1].TX_TLR = BPRFHIS1.TX_TLR;
        BPCFHTTT.TIMES[WS_I-1].MAKER = BPRFHIS1.MAKER;
        BPCFHTTT.TIMES[WS_I-1].SUP1 = BPRFHIS1.SUP1;
        BPCFHTTT.TIMES[WS_I-1].SUP2 = BPRFHIS1.SUP2;
        BPCFHTTT.TIMES[WS_I-1].TX_CHNL = BPRFHIS1.TX_CHNL;
        BPCFHTTT.TIMES[WS_I-1].TX_CHNL_NM = " ";
        BPCFHTTT.TIMES[WS_I-1].TX_REQFM = BPRFHIS1.TX_CHNL;
        BPCFHTTT.TIMES[WS_I-1].VCHNO = BPRFHIS1.VCHNO;
        BPCFHTTT.TIMES[WS_I-1].TX_VAL_DT = BPRFHIS1.TX_VAL_DT;
        BPCFHTTT.TIMES[WS_I-1].STS = BPRFHIS1.TX_STS;
        BPCFHTTT.TIMES[WS_I-1].PRINT_FLG = BPRFHIS1.PRINT_FLG;
        BPCFHTTT.TIMES[WS_I-1].REMARK = BPRFHIS1.REMARK;
        BPCFHTTT.TIMES[WS_I-1].NARRATIVE = BPRFHIS1.NARRATIVE;
        BPCFHTTT.TIMES[WS_I-1].OTH_AC = BPRFHIS1.OTH_AC;
        BPCFHTTT.TIMES[WS_I-1].OTH_TX_TOOL = BPRFHIS1.OTH_TX_TOOL;
        BPCFHTTT.TIMES[WS_I-1].REL_AC = BPRFHIS1.RLT_AC;
        BPCFHTTT.TIMES[WS_I-1].REL_CI_NAME = BPRFHIS1.RLT_AC_NAME;
        BPCFHTTT.TIMES[WS_I-1].RLT_TX_TOOL = BPRFHIS1.RLT_TX_TOOL;
        BPCFHTTT.TIMES[WS_I-1].RLT_BANK = BPRFHIS1.RLT_BANK;
        BPCFHTTT.TIMES[WS_I-1].REL_BK_NM = BPRFHIS1.RLT_BK_NM;
        BPCFHTTT.TIMES[WS_I-1].RLT_REF_NO = BPRFHIS1.RLT_REF_NO;
        BPCFHTTT.TIMES[WS_I-1].RLT_CCY = BPRFHIS1.RLT_CCY;
        BPCFHTTT.TIMES[WS_I-1].TX_REV_DT = BPRFHIS1.TX_REV_DT;
        BPCFHTTT.TIMES[WS_I-1].ORG_AC_DT = BPRFHIS1.ORG_AC_DT;
        BPCFHTTT.TIMES[WS_I-1].ORG_JRNNO = BPRFHIS1.ORG_JRNNO;
        WS_I = (short) (WS_I + 1);
        BPCFHTTT.TOTAL_NUM = (short) (BPCFHTTT.TOTAL_NUM + 1);
        BPCFHTTT.TOD_REC_NUM = (short) (BPCFHTTT.TOD_REC_NUM + 1);
        JIBS_tmp_str[0] = "" + BPCFHTTT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFHTTT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFHIS1.KEY.AC_DT;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(8);
        BPCFHTTT.END_POS = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + BPCFHTTT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFHTTT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFHIS1.KEY.JRNNO;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 9 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(9 + 12 - 1);
        BPCFHTTT.END_POS = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + BPCFHTTT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFHTTT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFHIS1.KEY.JRN_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 21 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(21 + 4 - 1);
        BPCFHTTT.END_POS = Long.parseLong(JIBS_NumStr);
    }
    public void B060_01_DATA_TRANS_TO_FMT2() throws IOException,SQLException,Exception {
        BPCFHTTT.TIMES[WS_I-1].TX_CD = BPRFHIS2.TX_CD;
        BPCFHTTT.TIMES[WS_I-1].TX_MMO = BPRFHIS2.TX_MMO;
        BPCFHTTT.TIMES[WS_I-1].TX_MMO_NM = " ";
        BPCFHTTT.TIMES[WS_I-1].TX_DT = BPRFHIS2.TX_DT;
        BPCFHTTT.TIMES[WS_I-1].TX_TM = BPRFHIS2.TX_TM;
        BPCFHTTT.TIMES[WS_I-1].TX_SYS_JRN = BPRFHIS2.TX_SYS_JRN;
        BPCFHTTT.TIMES[WS_I-1].AC_DT = BPRFHIS2.KEY.AC_DT;
        BPCFHTTT.TIMES[WS_I-1].JRNNO = BPRFHIS2.KEY.JRNNO;
        BPCFHTTT.TIMES[WS_I-1].JRN_SEQ = BPRFHIS2.KEY.JRN_SEQ;
        BPCFHTTT.TIMES[WS_I-1].CI_NO = BPRFHIS2.CI_NO;
        BPCFHTTT.TIMES[WS_I-1].BV_CODE = BPRFHIS2.BV_CODE;
        BPCFHTTT.TIMES[WS_I-1].HEAD_NO = BPRFHIS2.HEAD_NO;
        BPCFHTTT.TIMES[WS_I-1].BV_NO = BPRFHIS2.BV_NO;
        BPCFHTTT.TIMES[WS_I-1].DRCR_FLG = BPRFHIS2.DRCRFLG;
        BPCFHTTT.TIMES[WS_I-1].AC = BPRFHIS2.KEY.AC;
        BPCFHTTT.TIMES[WS_I-1].ACO_AC = BPRFHIS2.ACO_AC;
        BPCFHTTT.TIMES[WS_I-1].TX_CCY = BPRFHIS2.TX_CCY;
        BPCFHTTT.TIMES[WS_I-1].CCY_TYP = BPRFHIS2.TX_CCY_TYPE;
        BPCFHTTT.TIMES[WS_I-1].AC_SEQ = BPB7070_AWA_7070.AC_SEQ;
        BPCFHTTT.TIMES[WS_I-1].TX_TYPE = BPRFHIS2.TX_TYPE;
        BPCFHTTT.TIMES[WS_I-1].TX_AMT = BPRFHIS2.TX_AMT;
        BPCFHTTT.TIMES[WS_I-1].CUR_BAL = BPRFHIS2.CURR_BAL;
        BPCFHTTT.TIMES[WS_I-1].PROD_CD = BPRFHIS2.PROD_CD;
        BPCFHTTT.TIMES[WS_I-1].REF_NO = BPRFHIS2.REF_NO;
        BPCFHTTT.TIMES[WS_I-1].TX_TOOL = BPRFHIS2.TX_TOOL;
        BPCFHTTT.TIMES[WS_I-1].APP_MMO = BPRFHIS2.APP_MMO;
        BPCFHTTT.TIMES[WS_I-1].TX_BR = BPRFHIS2.TX_BR;
        BPCFHTTT.TIMES[WS_I-1].TX_DP = BPRFHIS2.TX_DP;
        BPCFHTTT.TIMES[WS_I-1].TX_TLR = BPRFHIS2.TX_TLR;
        BPCFHTTT.TIMES[WS_I-1].MAKER = BPRFHIS2.MAKER;
        BPCFHTTT.TIMES[WS_I-1].SUP1 = BPRFHIS2.SUP1;
        BPCFHTTT.TIMES[WS_I-1].SUP2 = BPRFHIS2.SUP2;
        BPCFHTTT.TIMES[WS_I-1].TX_CHNL = BPRFHIS2.TX_CHNL;
        BPCFHTTT.TIMES[WS_I-1].TX_CHNL_NM = " ";
        BPCFHTTT.TIMES[WS_I-1].TX_REQFM = BPRFHIS2.TX_CHNL;
        BPCFHTTT.TIMES[WS_I-1].VCHNO = BPRFHIS2.VCHNO;
        BPCFHTTT.TIMES[WS_I-1].TX_VAL_DT = BPRFHIS2.TX_VAL_DT;
        BPCFHTTT.TIMES[WS_I-1].STS = BPRFHIS2.TX_STS;
        BPCFHTTT.TIMES[WS_I-1].PRINT_FLG = BPRFHIS2.PRINT_FLG;
        BPCFHTTT.TIMES[WS_I-1].REMARK = BPRFHIS2.REMARK;
        BPCFHTTT.TIMES[WS_I-1].NARRATIVE = BPRFHIS2.NARRATIVE;
        BPCFHTTT.TIMES[WS_I-1].OTH_AC = BPRFHIS2.OTH_AC;
        BPCFHTTT.TIMES[WS_I-1].OTH_TX_TOOL = BPRFHIS2.OTH_TX_TOOL;
        BPCFHTTT.TIMES[WS_I-1].REL_AC = BPRFHIS2.RLT_AC;
        BPCFHTTT.TIMES[WS_I-1].REL_CI_NAME = BPRFHIS2.RLT_AC_NAME;
        BPCFHTTT.TIMES[WS_I-1].RLT_TX_TOOL = BPRFHIS2.RLT_TX_TOOL;
        BPCFHTTT.TIMES[WS_I-1].RLT_BANK = BPRFHIS2.RLT_BANK;
        BPCFHTTT.TIMES[WS_I-1].REL_BK_NM = BPRFHIS2.RLT_BK_NM;
        BPCFHTTT.TIMES[WS_I-1].RLT_REF_NO = BPRFHIS2.RLT_REF_NO;
        BPCFHTTT.TIMES[WS_I-1].RLT_CCY = BPRFHIS2.RLT_CCY;
        BPCFHTTT.TIMES[WS_I-1].TX_REV_DT = BPRFHIS2.TX_REV_DT;
        BPCFHTTT.TIMES[WS_I-1].ORG_AC_DT = BPRFHIS2.ORG_AC_DT;
        BPCFHTTT.TIMES[WS_I-1].ORG_JRNNO = BPRFHIS2.ORG_JRNNO;
        WS_I = (short) (WS_I + 1);
        BPCFHTTT.TOTAL_NUM = (short) (BPCFHTTT.TOTAL_NUM + 1);
        BPCFHTTT.TOD_REC_NUM = (short) (BPCFHTTT.TOD_REC_NUM + 1);
        JIBS_tmp_str[0] = "" + BPCFHTTT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFHTTT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFHIS2.KEY.AC_DT;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(8);
        BPCFHTTT.END_POS = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + BPCFHTTT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFHTTT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFHIS2.KEY.JRNNO;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 9 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(9 + 12 - 1);
        BPCFHTTT.END_POS = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + BPCFHTTT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFHTTT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFHIS2.KEY.JRN_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 21 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(21 + 4 - 1);
        BPCFHTTT.END_POS = Long.parseLong(JIBS_NumStr);
    }
    public void B060_01_DATA_TRANS_TO_FMT3() throws IOException,SQLException,Exception {
        BPCFHTTT.TIMES[WS_I-1].TX_CD = BPRFHIST.TX_CD;
        BPCFHTTT.TIMES[WS_I-1].TX_MMO = BPRFHIST.TX_MMO;
        BPCFHTTT.TIMES[WS_I-1].TX_MMO_NM = " ";
        BPCFHTTT.TIMES[WS_I-1].TX_DT = BPRFHIST.TX_DT;
        BPCFHTTT.TIMES[WS_I-1].TX_TM = BPRFHIST.TX_TM;
        BPCFHTTT.TIMES[WS_I-1].TX_SYS_JRN = BPRFHIST.TX_SYS_JRN;
        BPCFHTTT.TIMES[WS_I-1].AC_DT = BPRFHIST.KEY.AC_DT;
        BPCFHTTT.TIMES[WS_I-1].JRNNO = BPRFHIST.KEY.JRNNO;
        BPCFHTTT.TIMES[WS_I-1].JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
        BPCFHTTT.TIMES[WS_I-1].CI_NO = BPRFHIST.CI_NO;
        BPCFHTTT.TIMES[WS_I-1].BV_CODE = BPRFHIST.BV_CODE;
        BPCFHTTT.TIMES[WS_I-1].HEAD_NO = BPRFHIST.HEAD_NO;
        BPCFHTTT.TIMES[WS_I-1].BV_NO = BPRFHIST.BV_NO;
        BPCFHTTT.TIMES[WS_I-1].DRCR_FLG = BPRFHIST.DRCRFLG;
        BPCFHTTT.TIMES[WS_I-1].AC = BPRFHIST.KEY.AC;
        BPCFHTTT.TIMES[WS_I-1].ACO_AC = BPRFHIST.ACO_AC;
        BPCFHTTT.TIMES[WS_I-1].TX_CCY = BPRFHIST.TX_CCY;
        BPCFHTTT.TIMES[WS_I-1].CCY_TYP = BPRFHIST.TX_CCY_TYPE;
        BPCFHTTT.TIMES[WS_I-1].AC_SEQ = BPB7070_AWA_7070.AC_SEQ;
        BPCFHTTT.TIMES[WS_I-1].TX_TYPE = BPRFHIST.TX_TYPE;
        BPCFHTTT.TIMES[WS_I-1].TX_AMT = BPRFHIST.TX_AMT;
        BPCFHTTT.TIMES[WS_I-1].CUR_BAL = BPRFHIST.CURR_BAL;
        BPCFHTTT.TIMES[WS_I-1].PROD_CD = BPRFHIST.PROD_CD;
        BPCFHTTT.TIMES[WS_I-1].REF_NO = BPRFHIST.REF_NO;
        BPCFHTTT.TIMES[WS_I-1].TX_TOOL = BPRFHIST.TX_TOOL;
        BPCFHTTT.TIMES[WS_I-1].APP_MMO = BPRFHIST.APP_MMO;
        BPCFHTTT.TIMES[WS_I-1].TX_BR = BPRFHIST.TX_BR;
        BPCFHTTT.TIMES[WS_I-1].TX_DP = BPRFHIST.TX_DP;
        BPCFHTTT.TIMES[WS_I-1].TX_TLR = BPRFHIST.TX_TLR;
        BPCFHTTT.TIMES[WS_I-1].MAKER = BPRFHIST.MAKER;
        BPCFHTTT.TIMES[WS_I-1].SUP1 = BPRFHIST.SUP1;
        BPCFHTTT.TIMES[WS_I-1].SUP2 = BPRFHIST.SUP2;
        BPCFHTTT.TIMES[WS_I-1].TX_CHNL = BPRFHIST.TX_CHNL;
        BPCFHTTT.TIMES[WS_I-1].TX_CHNL_NM = " ";
        BPCFHTTT.TIMES[WS_I-1].TX_REQFM = BPRFHIST.TX_CHNL;
        BPCFHTTT.TIMES[WS_I-1].VCHNO = BPRFHIST.VCHNO;
        BPCFHTTT.TIMES[WS_I-1].TX_VAL_DT = BPRFHIST.TX_VAL_DT;
        BPCFHTTT.TIMES[WS_I-1].STS = BPRFHIST.TX_STS;
        BPCFHTTT.TIMES[WS_I-1].PRINT_FLG = BPRFHIST.PRINT_FLG;
        BPCFHTTT.TIMES[WS_I-1].REMARK = BPRFHIST.REMARK;
        BPCFHTTT.TIMES[WS_I-1].NARRATIVE = BPRFHIST.NARRATIVE;
        BPCFHTTT.TIMES[WS_I-1].OTH_AC = BPRFHIST.OTH_AC;
        BPCFHTTT.TIMES[WS_I-1].OTH_TX_TOOL = BPRFHIST.OTH_TX_TOOL;
        BPCFHTTT.TIMES[WS_I-1].REL_AC = BPRFHIST.RLT_AC;
        BPCFHTTT.TIMES[WS_I-1].REL_CI_NAME = BPRFHIST.RLT_AC_NAME;
        BPCFHTTT.TIMES[WS_I-1].RLT_TX_TOOL = BPRFHIST.RLT_TX_TOOL;
        BPCFHTTT.TIMES[WS_I-1].RLT_BANK = BPRFHIST.RLT_BANK;
        BPCFHTTT.TIMES[WS_I-1].REL_BK_NM = BPRFHIST.RLT_BK_NM;
        BPCFHTTT.TIMES[WS_I-1].RLT_REF_NO = BPRFHIST.RLT_REF_NO;
        BPCFHTTT.TIMES[WS_I-1].RLT_CCY = BPRFHIST.RLT_CCY;
        BPCFHTTT.TIMES[WS_I-1].TX_REV_DT = BPRFHIST.TX_REV_DT;
        BPCFHTTT.TIMES[WS_I-1].ORG_AC_DT = BPRFHIST.ORG_AC_DT;
        BPCFHTTT.TIMES[WS_I-1].ORG_JRNNO = BPRFHIST.ORG_JRNNO;
        WS_I = (short) (WS_I + 1);
        BPCFHTTT.TOTAL_NUM = (short) (BPCFHTTT.TOTAL_NUM + 1);
        BPCFHTTT.TOD_REC_NUM = (short) (BPCFHTTT.TOD_REC_NUM + 1);
        JIBS_tmp_str[0] = "" + BPCFHTTT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFHTTT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFHIST.KEY.AC_DT;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(8);
        BPCFHTTT.END_POS = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + BPCFHTTT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFHTTT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFHIST.KEY.JRNNO;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 9 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(9 + 12 - 1);
        BPCFHTTT.END_POS = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + BPCFHTTT.END_POS;
        JIBS_f0 = "";
        for (int i=0;i<24-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCFHTTT.END_POS;
        JIBS_tmp_str[1] = "" + BPRFHIST.KEY.JRN_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 21 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(21 + 4 - 1);
        BPCFHTTT.END_POS = Long.parseLong(JIBS_NumStr);
    }
    public void T000_STARTBR_JRNNO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            CEP.TRC(SCCGWA, "READ TABLE2");
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "AC_DT = :WS_STR_DT "
                + "AND JRNNO = :WS_JRNNO";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            CEP.TRC(SCCGWA, "READ TABLE1");
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "AC_DT = :WS_STR_DT "
                + "AND JRNNO = :WS_JRNNO";
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
    public void T000_STARTBR_JRNNO_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "AC_DT = :WS_STR_DT "
                + "AND JRNNO = :WS_JRNNO "
                + "AND JRN_SEQ = :WS_JRN_SEQ";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "AC_DT = :WS_STR_DT "
                + "AND JRNNO = :WS_JRNNO "
                + "AND JRN_SEQ = :WS_JRN_SEQ";
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
    public void T000_STARTBR_BRTLR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TLR "
                + "AND AC_DT = :WS_STR_DT";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TLR "
                + "AND AC_DT = :WS_STR_DT";
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
    public void T000_STARTBR_BRTLR_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TLR "
                + "AND AC_DT = :WS_STR_DT "
                + "AND ( ( JRNNO = :WS_JRNNO "
                + "AND JRN_SEQ > :WS_JRN_SEQ ) "
                + "OR JRNNO > :WS_JRNNO )";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TLR "
                + "AND AC_DT = :WS_STR_DT "
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
    public void T000_STARTBR_ACOAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                + "AND TX_STS LIKE :WS_STS";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                + "AND TX_STS LIKE :WS_STS";
            BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "READ NORMAL");
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
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                + "AND AC_DT = :WS_STR_DT "
                + "AND ( ( JRNNO = :WS_JRNNO "
                + "AND JRN_SEQ > :WS_JRN_SEQ ) "
                + "OR JRNNO > :WS_JRNNO )";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                + "AND AC_DT = :WS_STR_DT "
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
    public void T000_STARTBR_JRNNO3() throws IOException,SQLException,Exception {
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "AC_DT = :WS_STR_DT "
            + "AND JRNNO = :WS_JRNNO";
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
    public void T000_STARTBR_JRNNO_NEW3() throws IOException,SQLException,Exception {
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "AC_DT = :WS_STR_DT "
            + "AND JRNNO = :WS_JRNNO "
            + "AND JRN_SEQ = :WS_JRN_SEQ";
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
    public void T000_STARTBR_BRTLR3() throws IOException,SQLException,Exception {
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TLR "
            + "AND AC_DT = :WS_STR_DT";
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
    public void T000_STARTBR_BRTLR_NEW3() throws IOException,SQLException,Exception {
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TLR "
            + "AND AC_DT = :WS_STR_DT "
            + "AND ( ( JRNNO = :WS_JRNNO "
            + "AND JRN_SEQ = :WS_JRN_SEQ ) "
            + "OR JRNNO = :WS_JRNNO )";
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
    public void T000_STARTBR_ACOAC3() throws IOException,SQLException,Exception {
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
            + "AND TX_STS LIKE :WS_STS "
            + "AND AC_DT <= :WS_END_DT "
            + "AND AC_DT >= :WS_STR_DT";
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
    public void T000_STARTBR_ACOAC_NEW3() throws IOException,SQLException,Exception {
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
            + "AND AC_DT >= :WS_STR_DT";
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
    public void T000_STARTBR_ACNO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            if (BPB7070_AWA_7070.TX_AMT_F != 0) {
                if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND TX_AMT >= :WS_TX_AMT_FROM "
                        + "AND TX_AMT <= :WS_TX_AMT_TO "
                        + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                } else {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND TX_AMT >= :WS_TX_AMT_FROM "
                        + "AND TX_AMT <= :WS_TX_AMT_TO "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                }
            } else {
                if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                }
            }
        } else {
            if (BPB7070_AWA_7070.TX_AMT_F != 0) {
                if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND TX_AMT >= :WS_TX_AMT_FROM "
                        + "AND TX_AMT <= :WS_TX_AMT_TO "
                        + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                } else {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND TX_AMT >= :WS_TX_AMT_FROM "
                        + "AND TX_AMT <= :WS_TX_AMT_TO "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                    CEP.TRC(SCCGWA, BPRFHIS2.KEY.JRNNO);
                }
            } else {
                if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                }
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "READ NORMAL");
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CINO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            if (BPB7070_AWA_7070.TX_AMT_F != 0) {
                if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "CI_NO = :WS_CI_NO "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND TX_AMT >= :WS_TX_AMT_FROM "
                        + "AND TX_AMT <= :WS_TX_AMT_TO "
                        + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                } else {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "CI_NO = :WS_CI_NO "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND TX_AMT >= :WS_TX_AMT_FROM "
                        + "AND TX_AMT <= :WS_TX_AMT_TO "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                }
            } else {
                if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "CI_NO = :WS_CI_NO "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                }
            }
        } else {
            if (BPB7070_AWA_7070.TX_AMT_F != 0) {
                if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "CI_NO = :WS_CI_NO "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND TX_AMT >= :WS_TX_AMT_FROM "
                        + "AND TX_AMT <= :WS_TX_AMT_TO "
                        + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                } else {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "CI_NO = :WS_CI_NO "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND TX_AMT >= :WS_TX_AMT_FROM "
                        + "AND TX_AMT <= :WS_TX_AMT_TO "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                }
            } else {
                if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "CI_NO = :WS_CI_NO "
                        + "AND AC_DT = :WS_STR_DT "
                        + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                        + "AND TX_STS LIKE :WS_STS";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                }
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "READ NORMAL");
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACNO3() throws IOException,SQLException,Exception {
        if (BPB7070_AWA_7070.TX_AMT_F != 0) {
            if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND TX_AMT >= :WS_TX_AMT_FROM "
                    + "AND TX_AMT <= :WS_TX_AMT_TO "
                    + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                    + "AND TX_STS LIKE :WS_STS";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND TX_AMT >= :WS_TX_AMT_FROM "
                    + "AND TX_AMT <= :WS_TX_AMT_TO "
                    + "AND TX_STS LIKE :WS_STS";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        } else {
            if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                    + "AND TX_STS LIKE :WS_STS";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
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
    public void T000_STARTBR_CINO3() throws IOException,SQLException,Exception {
        if (BPB7070_AWA_7070.TX_AMT_F != 0) {
            if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "CI_NO = :WS_CI_NO "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND TX_AMT >= :WS_TX_AMT_FROM "
                    + "AND TX_AMT <= :WS_TX_AMT_TO "
                    + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                    + "AND TX_STS LIKE :WS_STS";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "CI_NO = :WS_CI_NO "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND TX_AMT >= :WS_TX_AMT_FROM "
                    + "AND TX_AMT <= :WS_TX_AMT_TO "
                    + "AND TX_STS LIKE :WS_STS";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        } else {
            if (BPB7070_AWA_7070.RL_AC_NM.trim().length() > 0) {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "CI_NO = :WS_CI_NO "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND RLT_AC_NAME = :WS_RLT_AC_NAME "
                    + "AND TX_STS LIKE :WS_STS";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
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
    public void T000_READNEXT_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            IBS.READNEXT(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            IBS.READNEXT(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
            CEP.TRC(SCCGWA, BPRFHIS2.KEY.JRNNO);
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
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
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
