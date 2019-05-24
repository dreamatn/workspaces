package com.hisun.BP;

import java.util.ArrayList;
import java.util.List;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZEXHOW {
    BPZEXHOW_WS_EX_HOL_DATA WS_EX_HOL_DATA;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_INQ_CAL_CODE = "BP-P-INQ-CAL-CODE";
    String K_CMP_MUL_WORK_DAY = "BP-P-MUL-WORK-DAY";
    String K_CMP_UPDATE_CALE = "BP-P-UPDATE-CALE ";
    String K_CMP_MAIN_BPTEXUPF = "BP-R-MAINT-UPDATA";
    String K_CMP_EX_HOL_CHECK = "BP-P-EXCEL-HOL-CHECK";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_REC_CNT = 0;
    String WS_CAL_CODE = " ";
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    char WS_DATE_TYPE = ' ';
    int WS_HOL_CNT = 0;
    List<BPZEXHOW_WS_EX_HOL_DATA> WS_EX_HOL_DATA = new ArrayList<BPZEXHOW_WS_EX_HOL_DATA>();
    char WS_STOP = ' ';
    char WS_CHK_HOL = ' ';
    char WS_CHK_CALE_TBL = ' ';
    char WS_HOLIDAY_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCUCALE BPCUCALE = new BPCUCALE();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCEXCHK BPCEXCHK = new BPCEXCHK();
    BPCRMUPD BPCRMUPD = new BPCRMUPD();
    BPREXUPF BPREXUPF = new BPREXUPF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCEXAPV BPCEXAPV;
    public void MP(SCCGWA SCCGWA, BPCEXAPV BPCEXAPV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXAPV = BPCEXAPV;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZEXHOW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXAPV.RC);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B200_BRW_EXUPF_FOR_CHK();
        if (pgmRtn) return;
        if (WS_CHK_HOL == 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_HOL_DATA_ERR, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_HOL_CNT > 0) {
            B300_UPDATE_CALE_TABLE();
            if (pgmRtn) return;
        }
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        CEP.TRC(SCCGWA, BPCEXAPV.RC);
        if (!BPCEXAPV.EXCEL_TYPE.equalsIgnoreCase("02")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_NOT_CP_HOLIDAY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCEXAPV.BATCH_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BATCH_NO_EMPTY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_BRW_EXUPF_FOR_CHK() throws IOException,SQLException,Exception {
        WS_CHK_HOL = 'P';
        IBS.init(SCCGWA, BPREXUPF);
        IBS.init(SCCGWA, BPCRMUPD);
        BPREXUPF.KEY.BATCH_NO = BPCEXAPV.BATCH_NO;
        BPCRMUPD.INFO.POINTER = BPREXUPF;
        BPCRMUPD.INFO.LEN = 183;
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'S';
        BPREXUPF.KEY.BATCH_NO = BPCEXAPV.BATCH_NO;
        CEP.TRC(SCCGWA, BPCEXAPV.BATCH_NO);
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        WS_STOP = 'N';
        WS_REC_CNT = 0;
        WS_HOL_CNT = 0;
        WS_EX_HOL_DATA = new BPZEXHOW_WS_EX_HOL_DATA();
        WS_EX_HOL_DATA.add(WS_EX_HOL_DATA);
        while (WS_STOP != 'Y') {
            BPCRMUPD.INFO.FUNC = 'B';
            BPCRMUPD.INFO.OPT = 'N';
            S000_CALL_BPZRMUPD();
            if (pgmRtn) return;
            if (BPCRMUPD.RETURN_INFO == 'N') {
                WS_STOP = 'Y';
            } else {
                if (BPCRMUPD.RETURN_INFO == 'F') {
                    if (BPREXUPF.KEY.BATCH_NO.equalsIgnoreCase(BPCEXAPV.BATCH_NO)) {
                        WS_REC_CNT = WS_REC_CNT + 1;
                        B200_01_CHK_REC_VALID();
                        if (pgmRtn) return;
                        if (WS_CHK_HOL == 'F') {
                            WS_STOP = 'Y';
                        }
                    } else {
                        WS_STOP = 'Y';
                    }
                } else {
                    WS_STOP = 'Y';
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_READ_BPTEXUPF_ERR, BPCEXAPV.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_DATA_EMPTY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'E';
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
    }
    public void B200_01_CHK_REC_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXCHK);
        BPCEXCHK.EXCEL_TYPE = BPCEXAPV.EXCEL_TYPE;
        BPCEXCHK.EXCEL_DATA = BPREXUPF.RECORD;
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_DATA);
        S000_CALL_BPZEXHOL();
        if (pgmRtn) return;
        if (BPCEXCHK.DATA_FLG == '0') {
            WS_CHK_HOL = 'F';
        } else {
            WS_EX_HOL_DATA = new BPZEXHOW_WS_EX_HOL_DATA();
            WS_EX_HOL_DATA.add(WS_EX_HOL_DATA);
            WS_HOL_CNT = WS_HOL_CNT + 1;
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD, WS_EX_HOL_DATA);
        }
        CEP.TRC(SCCGWA, BPCEXCHK.DATA_FLG);
    }
    public void B300_UPDATE_CALE_TABLE() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_HOL_CNT; WS_I += 1) {
            B300_01_CHECK_CALE_TBL();
            if (pgmRtn) return;
            if (WS_CHK_CALE_TBL == 'E') {
                CEP.TRC(SCCGWA, "BEGIN CHECK DATE TYPE");
                B300_02_CHECK_DATE_TYPE();
                if (pgmRtn) return;
                if (WS_EX_HOL_DATA.get(WS_I-1).WS_DATE_FLAG == 'H' 
                    && WS_HOLIDAY_FLG != 'H') {
                    CEP.TRC(SCCGWA, "SET HOLIDAY");
                    WS_DATE_TYPE = 'H';
                    B300_03_MODIFY_CALE_TBL();
                    if (pgmRtn) return;
                }
                if (WS_EX_HOL_DATA.get(WS_I-1).WS_DATE_FLAG == 'W' 
                    && WS_HOLIDAY_FLG != 'W') {
                    CEP.TRC(SCCGWA, "SET FULL WORKDAY");
                    WS_DATE_TYPE = 'W';
                    B300_03_MODIFY_CALE_TBL();
                    if (pgmRtn) return;
                }
                if (WS_EX_HOL_DATA.get(WS_I-1).WS_DATE_FLAG == 'S' 
                    && WS_HOLIDAY_FLG != 'S') {
                    CEP.TRC(SCCGWA, "SET HALF WORKDAY");
                    WS_DATE_TYPE = 'S';
                    B300_03_MODIFY_CALE_TBL();
                    if (pgmRtn) return;
                }
            }
            if (WS_CHK_CALE_TBL == 'N') {
                B300_04_ADD_CALE_TBL();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_01_CHECK_CALE_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCALE);
        BPCUCALE.FUNC = 'C';
        IBS.CPY2CLS(SCCGWA, WS_EX_HOL_DATA.get(WS_I-1).WS_HOL_DATE+"", BPCUCALE.UP_DATE);
        BPCUCALE.CAL_DETAIL.CNTYS_CD = WS_EX_HOL_DATA.get(WS_I-1).WS_CNTY_CD;
        BPCUCALE.CAL_DETAIL.CITY_CD = WS_EX_HOL_DATA.get(WS_I-1).WS_CITY_CD;
        CEP.TRC(SCCGWA, BPCUCALE.UP_DATE);
        CEP.TRC(SCCGWA, BPCUCALE.CAL_DETAIL.CNTYS_CD);
        CEP.TRC(SCCGWA, BPCUCALE.CAL_DETAIL.CITY_CD);
        S000_CALL_BPZUCALE();
        if (pgmRtn) return;
        if (BPCUCALE.RC.RC_CODE == 0) {
            if (BPCUCALE.CHECK_RESULT == 'E') {
                CEP.TRC(SCCGWA, "CALE TBL EXIST");
                WS_CHK_CALE_TBL = 'E';
                WS_CAL_CODE = BPCUCALE.CAL_CODE;
                WS_EFF_DATE = BPCUCALE.EFF_DATE;
                WS_EXP_DATE = BPCUCALE.EXP_DATE;
            } else {
                WS_CHK_CALE_TBL = 'N';
                CEP.TRC(SCCGWA, "CALE TBL NOTFND");
            }
        }
    }
    public void B300_02_CHECK_DATE_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCKWD);
        BPCOCKWD.DATE = WS_EX_HOL_DATA.get(WS_I-1).WS_HOL_DATE;
        BPCOCKWD.CAL_CODE = WS_CAL_CODE;
        BPCOCKWD.DAYE_FLG = 'Y';
        BPCOCKWD.STAT_FLG = 'Y';
        S000_CALL_BPZPCKWD();
        if (pgmRtn) return;
        if (BPCOCKWD.SPD_DAY[1-1] == 1) {
            WS_HOLIDAY_FLG = 'W';
        } else {
            WS_HOLIDAY_FLG = 'H';
        }
        if (BPCOCKWD.HALFDAY_FLG == 'Y') {
            WS_HOLIDAY_FLG = 'S';
        }
        CEP.TRC(SCCGWA, WS_HOLIDAY_FLG);
    }
    public void B300_03_MODIFY_CALE_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCALE);
        BPCUCALE.FUNC = 'U';
        BPCUCALE.DATE_TYPE = WS_DATE_TYPE;
        IBS.CPY2CLS(SCCGWA, WS_EX_HOL_DATA.get(WS_I-1).WS_HOL_DATE+"", BPCUCALE.UP_DATE);
        BPCUCALE.CAL_DETAIL.CNTYS_CD = WS_EX_HOL_DATA.get(WS_I-1).WS_CNTY_CD;
        BPCUCALE.CAL_DETAIL.CITY_CD = WS_EX_HOL_DATA.get(WS_I-1).WS_CITY_CD;
        BPCUCALE.EFF_DATE = WS_EFF_DATE;
        BPCUCALE.EXP_DATE = WS_EXP_DATE;
        S000_CALL_BPZUCALE();
        if (pgmRtn) return;
    }
    public void B300_04_ADD_CALE_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCALE);
        BPCUCALE.FUNC = 'A';
        BPCUCALE.DATE_TYPE = WS_EX_HOL_DATA.get(WS_I-1).WS_DATE_FLAG;
        IBS.CPY2CLS(SCCGWA, WS_EX_HOL_DATA.get(WS_I-1).WS_HOL_DATE+"", BPCUCALE.UP_DATE);
        BPCUCALE.CAL_DETAIL.CNTYS_CD = WS_EX_HOL_DATA.get(WS_I-1).WS_CNTY_CD;
        BPCUCALE.CAL_DETAIL.CITY_CD = WS_EX_HOL_DATA.get(WS_I-1).WS_CITY_CD;
        S000_CALL_BPZUCALE();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-WORK-DAY", BPCOCKWD);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZEXHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_EX_HOL_CHECK, BPCEXCHK);
        if (BPCEXCHK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCALE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_UPDATE_CALE, BPCUCALE);
        if (BPCUCALE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCALE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_BPTEXUPF, BPCRMUPD);
        CEP.TRC(SCCGWA, BPCRMUPD.RC);
        if (BPCRMUPD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMUPD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCEXAPV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCEXAPV = ");
            CEP.TRC(SCCGWA, BPCEXAPV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
