package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZITHOL {
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZITHOL";
    String CPN_R_BPZRTHOL = "BP-R-MAINT-THOL     ";
    String CPN_R_BPZRTWND = "BP-R-MAINT-TWND     ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    int WS_DATE = 0;
    BPZITHOL_WS_OUPUT_DAT WS_OUPUT_DAT = new BPZITHOL_WS_OUPUT_DAT();
    char WS_FOUND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRTHOL BPCRTHOL = new BPCRTHOL();
    BPCRTWND BPCRTWND = new BPCRTWND();
    BPRTHOL BPRTHOL = new BPRTHOL();
    BPRTWND BPRTWND = new BPRTWND();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRCNTY BPRRCNTY = new BPRCNTY();
    SCCGWA SCCGWA;
    BPCITHOL BPCITHOL;
    public void MP(SCCGWA SCCGWA, BPCITHOL BPCITHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCITHOL = BPCITHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZITHOL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTHOL);
        IBS.init(SCCGWA, BPCRTWND);
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPRTWND);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCITHOL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_LEGAL_HOLIDAY();
        if (pgmRtn) return;
        B030_GET_WEEKND();
        if (pgmRtn) return;
        for (WS_J = 1; WS_J <= 50; WS_J += 1) {
            CEP.TRC(SCCGWA, BPCITHOL.OUPUT_DAT.HOL_TXT.HOL_DATA[WS_J-1].HOL_DT);
        }
        for (WS_J = 1; WS_J <= 7; WS_J += 1) {
            CEP.TRC(SCCGWA, BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_DATA[WS_J-1].WEEKND);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCITHOL.INPUT_DAT.INQUIRE_DATE);
        if (BPCITHOL.INPUT_DAT.INQUIRE_DATE == 0) {
            BPCITHOL.INPUT_DAT.INQUIRE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, BPCITHOL.INPUT_DAT.INQUIRE_DATE);
    }
    public void B020_GET_LEGAL_HOLIDAY() throws IOException,SQLException,Exception {
        BPCRTHOL.FUNC = 'B';
        BPCRTHOL.OPT = 'S';
        BPRTHOL.KEY.CAL_CD = BPCITHOL.INPUT_DAT.CAL_CD;
        CEP.TRC(SCCGWA, BPRTHOL.KEY);
        BPCRTHOL.DATA_LEN = 54;
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        WS_FOUND_FLG = 'Y';
        while (WS_FOUND_FLG != 'N') {
            IBS.init(SCCGWA, BPRTHOL);
            BPCRTHOL.FUNC = 'B';
            BPCRTHOL.OPT = 'N';
            S000_CALL_BPZRTHOL();
            if (pgmRtn) return;
            if (BPCRTHOL.RETURN_INFO == 'N') {
                WS_FOUND_FLG = 'N';
            } else {
                R000_TRANS_HOL_WS();
                if (pgmRtn) return;
                R000_TRANS_HOL_OUTPUT();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCITHOL.OUPUT_DAT.HOL_TXT.HOL_EFF_DT);
        CEP.TRC(SCCGWA, WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT);
        if (BPCITHOL.OUPUT_DAT.HOL_TXT.HOL_EFF_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCITHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRTHOL.FUNC = 'B';
        BPCRTHOL.OPT = 'E';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
    }
    public void B030_GET_WEEKND() throws IOException,SQLException,Exception {
        BPCRTWND.INFO.FUNC = 'B';
        BPCRTWND.INFO.OPT = 'S';
        if (BPCITHOL.INPUT_DAT.CNTY_CD.trim().length() > 0 
            && BPCITHOL.INPUT_DAT.CAL_CD.trim().length() == 0) {
            IBS.init(SCCGWA, BPRRCNTY);
            IBS.init(SCCGWA, BPCPRMM);
            BPRRCNTY.KEY.TYP = "CNTY";
            BPRRCNTY.KEY.CD = BPCITHOL.INPUT_DAT.CNTY_CD;
            BPCPRMM.FUNC = '3';
            S010_CALL_BPZPRMM();
            if (pgmRtn) return;
            BPRTWND.KEY.CAL_CD = BPRRCNTY.DATA_TXT.CALR_CODE;
        }
        if (BPCITHOL.INPUT_DAT.CNTY_CD.trim().length() == 0 
            && BPCITHOL.INPUT_DAT.CAL_CD.trim().length() > 0) {
            BPRTWND.KEY.CAL_CD = BPCITHOL.INPUT_DAT.CAL_CD;
        }
        BPCRTWND.INFO.DATA_LEN = 68;
        S000_CALL_BPZRTWND();
        if (pgmRtn) return;
        WS_FOUND_FLG = 'Y';
        while (WS_FOUND_FLG != 'N') {
            IBS.init(SCCGWA, BPRTWND);
            BPCRTWND.INFO.FUNC = 'B';
            BPCRTWND.INFO.OPT = 'N';
            S000_CALL_BPZRTWND();
            if (pgmRtn) return;
            if (BPCRTWND.RETURN_INFO == 'N') {
                WS_FOUND_FLG = 'N';
            } else {
                R000_TRANS_WND_WS();
                if (pgmRtn) return;
                R000_TRANS_WND_OUTPUT();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_EFF_DT);
        if (BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_EFF_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCITHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRTWND.INFO.FUNC = 'B';
        BPCRTWND.INFO.OPT = 'E';
        S000_CALL_BPZRTWND();
        if (pgmRtn) return;
    }
    public void S010_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPRRCNTY.DATA_LEN = 78;
        BPCPRMM.DAT_PTR = BPRRCNTY;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCITHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_HOL_WS() throws IOException,SQLException,Exception {
        WS_J = 1;
        WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT = BPRTHOL.KEY.EFF_DATE;
        CEP.TRC(SCCGWA, WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT);
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            if (&& BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_DT > 0) {
                if (BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_OPT == 'A' 
                    || BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_OPT == 'N') {
                    WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_DATA[WS_J-1].WS_HOL_DT = BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_DT;
                    WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_DATA[WS_J-1].WS_HOL_DAYS = BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_CNT;
                    WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_DATA[WS_J-1].WS_HOL_NAME = BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_NAME;
                    WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_DATA[WS_J-1].WS_HOL_TPY = BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_TPY;
                    WS_J += 1;
                    if (BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_CNT > 1 
                        && BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_TPY == '0') {
                        WS_DATE = BPCITHOL.INPUT_DAT.INQUIRE_DATE;
                        JIBS_tmp_str[0] = "" + WS_DATE;
                        JIBS_f0 = "";
                        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                        JIBS_NumStr = JIBS_f0 + WS_DATE;
                        JIBS_tmp_str[1] = "" + BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_DT;
                        JIBS_tmp_int = JIBS_tmp_str[1].length();
                        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 4 - 1);
                        WS_DATE = Integer.parseInt(JIBS_NumStr);
                        for (WS_K = 2; WS_K <= BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_CNT; WS_K += 1) {
                            CEP.TRC(SCCGWA, WS_DATE);
                            IBS.init(SCCGWA, SCCCLDT);
                            SCCCLDT.DATE1 = WS_DATE;
                            SCCCLDT.DAYS = 1;
                            SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                            SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                            if (SCCCLDT.RC != 0) {
                                BPCITHOL.RC.RC_MMO = "SC";
                                BPCITHOL.RC.RC_CODE = SCCCLDT.RC;
                                Z_RET();
                                if (pgmRtn) return;
                            }
                            WS_DATE = SCCCLDT.DATE2;
                            WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_DATA[WS_J-1].WS_HOL_DT = (short) WS_DATE;
                            WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_DATA[WS_J-1].WS_HOL_DAYS = BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_CNT;
                            WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_DATA[WS_J-1].WS_HOL_NAME = BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_NAME;
                            WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_DATA[WS_J-1].WS_HOL_TPY = BPRTHOL.REDEFINES7.REDEFINES9.HOL_DATA[WS_I-1].HOL_TPY;
                            CEP.TRC(SCCGWA, WS_J);
                            CEP.TRC(SCCGWA, WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_DATA[WS_J-1].WS_HOL_DT);
                            WS_J += 1;
                        }
                    }
                }
            }
        }
    }
    public void R000_TRANS_HOL_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCITHOL.INPUT_DAT.QUERY_BY_DT == 'Y') {
            if (WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT <= BPCITHOL.INPUT_DAT.INQUIRE_DATE 
                && WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT > BPCITHOL.OUPUT_DAT.HOL_TXT.HOL_EFF_DT) {
                IBS.CPY2CLS(SCCGWA, WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT+"", BPCITHOL.OUPUT_DAT.HOL_TXT);
                WS_FOUND_FLG = 'N';
            }
        } else {
            if (WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                && WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT > BPCITHOL.OUPUT_DAT.HOL_TXT.HOL_EFF_DT) {
                IBS.init(SCCGWA, BPCITHOL.OUPUT_DAT.HOL_TXT);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUPUT_DAT.WS_HOL_TXT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCITHOL.OUPUT_DAT.HOL_TXT);
            }
            if (WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                && (WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT < BPCITHOL.OUPUT_DAT_2.HOL_TXT_2.HOL_EFF_DT_2 
                || BPCITHOL.OUPUT_DAT_2.HOL_TXT_2.HOL_EFF_DT_2 == 0)) {
                IBS.init(SCCGWA, BPCITHOL.OUPUT_DAT_2.HOL_TXT_2);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUPUT_DAT.WS_HOL_TXT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCITHOL.OUPUT_DAT_2.HOL_TXT_2);
            }
        }
    }
    public void R000_TRANS_WND_WS() throws IOException,SQLException,Exception {
        WS_OUPUT_DAT.WS_WEEKND_TXT.WS_WEEKND_EFF_DT = BPRTWND.KEY.EFF_DATE;
        for (WS_I = 1; WS_I <= 7; WS_I += 1) {
            WS_OUPUT_DAT.WS_WEEKND_TXT.WS_WEEKND_DATA[WS_I-1].WS_WEEKND = BPRTWND.REDEFINES5.WND_DATA[WS_I-1].WND_NO;
        }
    }
    public void R000_TRANS_WND_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OUPUT_DAT.WS_WEEKND_TXT.WS_WEEKND_EFF_DT);
        if (BPCITHOL.INPUT_DAT.QUERY_BY_DT == 'Y') {
            if (WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT <= BPCITHOL.INPUT_DAT.INQUIRE_DATE 
                && WS_OUPUT_DAT.WS_HOL_TXT.WS_HOL_EFF_DT > BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_EFF_DT) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUPUT_DAT.WS_WEEKND_TXT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCITHOL.OUPUT_DAT.WEEKND_TXT);
            }
        } else {
            if (WS_OUPUT_DAT.WS_WEEKND_TXT.WS_WEEKND_EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                && WS_OUPUT_DAT.WS_WEEKND_TXT.WS_WEEKND_EFF_DT > BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_EFF_DT) {
                IBS.init(SCCGWA, BPCITHOL.OUPUT_DAT.WEEKND_TXT);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUPUT_DAT.WS_WEEKND_TXT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCITHOL.OUPUT_DAT.WEEKND_TXT);
            }
            if (WS_OUPUT_DAT.WS_WEEKND_TXT.WS_WEEKND_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                && (WS_OUPUT_DAT.WS_WEEKND_TXT.WS_WEEKND_EFF_DT < BPCITHOL.OUPUT_DAT_2.WEEKND_TXT_2.WEEKND_EFF_DT_2 
                || BPCITHOL.OUPUT_DAT_2.WEEKND_TXT_2.WEEKND_EFF_DT_2 == 0)) {
                IBS.init(SCCGWA, BPCITHOL.OUPUT_DAT.WEEKND_TXT);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUPUT_DAT.WS_WEEKND_TXT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCITHOL.OUPUT_DAT_2.WEEKND_TXT_2);
            }
        }
    }
    public void S000_CALL_BPZRTHOL() throws IOException,SQLException,Exception {
        BPCRTHOL.POINTER = BPRTHOL;
        IBS.CALLCPN(SCCGWA, CPN_R_BPZRTHOL, BPCRTHOL);
        CEP.TRC(SCCGWA, BPCRTHOL.RC.RC_CODE);
        if (BPCRTHOL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTHOL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCITHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTWND() throws IOException,SQLException,Exception {
        BPCRTWND.INFO.POINTER = BPRTWND;
        IBS.CALLCPN(SCCGWA, CPN_R_BPZRTWND, BPCRTWND);
        CEP.TRC(SCCGWA, BPCRTWND.RC.RC_CODE);
        if (BPCRTWND.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTWND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCITHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCITHOL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCITHOL = ");
            CEP.TRC(SCCGWA, BPCITHOL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
