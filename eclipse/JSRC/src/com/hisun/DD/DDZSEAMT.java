package com.hisun.DD;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSEAMT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_SQL_AC = " ";
    String WS_SQL_CCY = " ";
    int WS_SQL_ST_DT = 0;
    int WS_SQL_ED_DT = 0;
    int K_MIN_DT = 00010101;
    int K_SPT_MIN_DT = 16001231;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_MSG_ID = "      ";
    double WS_BAL_SUM = 0;
    int WS_START_DT = 0;
    short WS_DAYS = 0;
    int WS_END_DT = 0;
    int WS_AC_DT_2 = 0;
    DDZSEAMT_WS_ARRAY[] WS_ARRAY = new DDZSEAMT_WS_ARRAY[100];
    double WS_CUR_BAL_CVS = 0;
    int WS_IN_DT = 0;
    DDZSEAMT_REDEFINES13 REDEFINES13 = new DDZSEAMT_REDEFINES13();
    int WS_OUT_DT = 0;
    DDZSEAMT_REDEFINES18 REDEFINES18 = new DDZSEAMT_REDEFINES18();
    int WS_CNT = 0;
    int WS_CNT2 = 0;
    int WS_CNT3 = 0;
    int WS_YEAR_CNT = 0;
    int WS_MTH_CNT = 0;
    char WS_TBL_FLAG = ' ';
    char WS_TBL_FLAG_2 = ' ';
    char WS_3_MTH_RECORD = 'Y';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCIFHSA BPCIFHSA = new BPCIFHSA();
    BPRFHISA BPRFHISA = new BPRFHISA();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    DDCSEAMT DDCSEAMT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public DDZSEAMT() {
        for (int i=0;i<100;i++) WS_ARRAY[i] = new DDZSEAMT_WS_ARRAY();
    }
    public void MP(SCCGWA SCCGWA, DDCSEAMT DDCSEAMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSEAMT = DDCSEAMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSEAMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DDCSEAMT.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CAL_DATE();
        if (pgmRtn) return;
        B300_CAL_AMT();
        if (pgmRtn) return;
        if (WS_CNT > 0) {
            B310_CAL_AVE_AMT();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDCSEAMT.I_AREA.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCSEAMT.O_AREA.MSG_ID);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCSEAMT.I_AREA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCSEAMT.O_AREA.MSG_ID);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCSEAMT.I_AREA.REF_DT == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_VAL_DT_INVALID, DDCSEAMT.O_AREA.MSG_ID);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_CAL_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.MTHS = -3;
        SCCCLDT.DATE1 = DDCSEAMT.I_AREA.REF_DT;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        DDCSEAMT.O_AREA.START_DT = SCCCLDT.DATE2;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DAYS = -1;
        SCCCLDT.DATE1 = DDCSEAMT.I_AREA.REF_DT;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_END_DT = SCCCLDT.DATE2;
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = DDCSEAMT.I_AREA.AC_NO;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.OPEN_DATE);
        if (DDCIMMST.DATA.OPEN_DATE > DDCSEAMT.O_AREA.START_DT) {
            DDCSEAMT.O_AREA.START_DT = DDCIMMST.DATA.OPEN_DATE;
        }
        CEP.TRC(SCCGWA, DDCSEAMT.O_AREA.START_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
    }
    public void B300_CAL_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFHSA);
        IBS.init(SCCGWA, BPRFHISA);
        BPCIFHSA.DATA.FUNC = '1';
        BPCIFHSA.DATA.AC = DDCSEAMT.I_AREA.AC_NO;
        BPCIFHSA.DATA.CCY = DDCSEAMT.I_AREA.CCY;
        BPCIFHSA.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCIFHSA.DATA.AC_DT);
        BPCIFHSA.DATA.REC_LEN = 144;
        BPCIFHSA.DATA.REC_PT = BPRFHISA;
        S000_CALL_BPZIFHSA();
        if (pgmRtn) return;
        BPCIFHSA.DATA.FUNC = '2';
        S000_CALL_BPZIFHSA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHISA.AC_DT);
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
        CEP.TRC(SCCGWA, BPRFHISA.L_BAL);
        WS_CNT = 0;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCIFHSA.RC);
        if (BPRFHISA.AC_DT < DDCSEAMT.O_AREA.START_DT 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            WS_CNT += 1;
            WS_ARRAY[WS_CNT-1].WS_AC_DT = BPRFHISA.AC_DT;
            WS_ARRAY[WS_CNT-1].WS_CUR_BAL = BPRFHISA.CUR_BAL;
            WS_ARRAY[WS_CNT-1].WS_L_BAL = BPRFHISA.L_BAL;
            WS_3_MTH_RECORD = 'N';
        } else {
            WS_3_MTH_RECORD = 'Y';
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHSA.RC);
            while ((BPRFHISA.AC_DT >= DDCSEAMT.O_AREA.START_DT) 
                && !(JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) 
                && WS_CNT < 100) {
                WS_CNT += 1;
                WS_ARRAY[WS_CNT-1].WS_AC_DT = BPRFHISA.AC_DT;
                WS_ARRAY[WS_CNT-1].WS_CUR_BAL = BPRFHISA.CUR_BAL;
                WS_ARRAY[WS_CNT-1].WS_L_BAL = BPRFHISA.L_BAL;
                BPCIFHSA.DATA.FUNC = '2';
                S000_CALL_BPZIFHSA();
                if (pgmRtn) return;
            }
            if (BPRFHISA.AC_DT < DDCSEAMT.O_AREA.START_DT) {
                WS_CUR_BAL_CVS = BPRFHISA.CUR_BAL;
            }
        }
        BPCIFHSA.DATA.FUNC = '3';
        S000_CALL_BPZIFHSA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT);
    }
    public void B310_CAL_AVE_AMT() throws IOException,SQLException,Exception {
        WS_BAL_SUM = 0;
        WS_CNT2 = 0;
        WS_CNT3 = 0;
        WS_AC_DT_2 = 1;
        CEP.TRC(SCCGWA, WS_3_MTH_RECORD);
        if (WS_3_MTH_RECORD == 'Y') {
            while (WS_CNT2 < WS_CNT 
                && WS_AC_DT_2 != 0) {
                WS_CNT2 += 1;
                WS_AC_DT_2 = WS_ARRAY[WS_CNT2-1].WS_AC_DT;
                if (WS_CNT2 == 1) {
                    if (WS_ARRAY[1-1].WS_AC_DT == WS_END_DT) {
                        WS_BAL_SUM = WS_BAL_SUM + WS_ARRAY[1-1].WS_CUR_BAL;
                    } else {
                        IBS.init(SCCGWA, SCCCLDT);
                        SCCCLDT.DATE2 = WS_END_DT;
                        SCCCLDT.DATE1 = WS_ARRAY[1-1].WS_AC_DT;
                        S000_CALL_SCSSCLDT();
                        if (pgmRtn) return;
                        WS_DAYS = (short) SCCCLDT.DAYS;
                        WS_BAL_SUM = WS_BAL_SUM + WS_ARRAY[1-1].WS_CUR_BAL * ( WS_DAYS + 1 );
                    }
                } else {
                    WS_CNT3 = WS_CNT2 - 1;
                    if (WS_ARRAY[WS_CNT2-1].WS_AC_DT != 0) {
                        IBS.init(SCCGWA, SCCCLDT);
                        SCCCLDT.DATE2 = WS_ARRAY[WS_CNT3-1].WS_AC_DT;
                        SCCCLDT.DATE1 = WS_ARRAY[WS_CNT2-1].WS_AC_DT;
                        S000_CALL_SCSSCLDT();
                        if (pgmRtn) return;
                        WS_DAYS = (short) SCCCLDT.DAYS;
                        WS_BAL_SUM = WS_BAL_SUM + WS_ARRAY[WS_CNT2-1].WS_CUR_BAL * WS_DAYS;
                    }
                }
            }
            if (WS_ARRAY[WS_CNT2-1].WS_AC_DT > DDCSEAMT.O_AREA.START_DT) {
                if (WS_ARRAY[WS_CNT2-1].WS_L_BAL == 0 
                    && WS_CUR_BAL_CVS > 0) {
                    WS_ARRAY[WS_CNT2-1].WS_L_BAL = WS_CUR_BAL_CVS;
                }
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE2 = WS_ARRAY[WS_CNT2-1].WS_AC_DT;
                SCCCLDT.DATE1 = DDCSEAMT.O_AREA.START_DT;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DAYS = (short) SCCCLDT.DAYS;
                WS_BAL_SUM = WS_BAL_SUM + WS_ARRAY[WS_CNT2-1].WS_L_BAL * WS_DAYS;
            }
        } else {
        }
        IBS.init(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, DDCSEAMT.O_AREA.START_DT);
        SCCCLDT.DATE2 = WS_END_DT;
        SCCCLDT.DATE1 = DDCSEAMT.O_AREA.START_DT;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DAYS = (short) SCCCLDT.DAYS;
        WS_DAYS = (short) (WS_DAYS + 1);
        if (WS_3_MTH_RECORD == 'N') {
            WS_BAL_SUM = BPRFHISA.CUR_BAL * WS_DAYS;
        }
        CEP.TRC(SCCGWA, WS_DAYS);
        CEP.TRC(SCCGWA, WS_BAL_SUM);
        if (WS_DAYS > 0) {
            DDCSEAMT.O_AREA.AVE_AMT = WS_BAL_SUM / WS_DAYS;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        CEP.TRC(SCCGWA, DDCIMMST.RC);
        if (DDCIMMST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCSEAMT.O_AREA.MSG_ID);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, SCCCLDT.RC+"", DDCSEAMT.O_AREA.MSG_ID);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZIFHSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INQ-FHISA", BPCIFHSA);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCCLDT.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCCLDT.RC);

            || JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHSA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCSEAMT.O_AREA.MSG_ID);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
