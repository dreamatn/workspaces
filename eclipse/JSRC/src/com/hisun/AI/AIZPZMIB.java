package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPZMIB {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    AICPZMIB_OUT_OCCURS OUT_OCCURS;
    brParm AITMIB_BR = new brParm();
    String WS_ERR_MSG = " ";
    int WS_AU1_BR = 0;
    int WS_AU2_BR = 0;
    int WS_INT_X = 0;
    char WS_AITHIS_FLG = ' ';
    short WS_NUM = 0;
    String K_CODE_AU1 = "AU1";
    String K_CODE_AU2 = "AU2";
    String K_CAL_CODE = "CN";
    char K_EOD_FLG = 'D';
    char K_EOM_FLG = 'M';
    char K_EOY_FLG = 'Y';
    short K_LAST_MON = 12;
    char WS_EOM = ' ';
    char WS_EOY = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRMIB AIRMIB = new AIRMIB();
    BPCPRMB BPCPRMB = new BPCPRMB();
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCOCKEM BPCOCKEM = new BPCOCKEM();
    SCCGWA SCCGWA;
    AICSMAPS AICSMAPS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICPZMIB AICPZMIB;
    public void MP(SCCGWA SCCGWA, AICPZMIB AICPZMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPZMIB = AICPZMIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIZPZMIB return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCKEM);
        BPCOCKEM.CAL_CODE = K_CAL_CODE;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) BPCOCKEM.YEAR = 0;
        else BPCOCKEM.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_NUM = 0;
        else WS_NUM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        CEP.TRC(SCCGWA, BPCOCKEM.YEAR);
        S000_CALL_BPZPCKEM();
        CEP.TRC(SCCGWA, BPCOCKEM.EOM_DATE[WS_NUM-1].EOM_DAY);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo(BPCOCKEM.EOM_DATE[WS_NUM-1].EOM_DAY) >= 0) {
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase(K_LAST_MON+"")) {
                WS_EOM = K_EOM_FLG;
                WS_EOY = K_EOY_FLG;
                CEP.TRC(SCCGWA, "111111");
            } else {
                WS_EOM = K_EOM_FLG;
                WS_EOY = K_EOD_FLG;
                CEP.TRC(SCCGWA, "222222");
            }
        } else {
            WS_EOM = K_EOD_FLG;
            WS_EOY = K_EOD_FLG;
        }
        CEP.TRC(SCCGWA, WS_EOM);
        CEP.TRC(SCCGWA, WS_EOY);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == WS_AU1_BR 
            || SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == WS_AU2_BR) {
            T000_STARTBR_AITMIB_1();
        } else {
            CEP.TRC(SCCGWA, AICPZMIB.BR);
            if (AICPZMIB.BR == 0) {
                AIRMIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            } else {
                AIRMIB.KEY.BR = AICPZMIB.BR;
            }
            T000_STARTBR_AITMIB_2();
        }
        T000_READNEXT_AITMIB();
        CEP.TRC(SCCGWA, WS_AU1_BR);
        CEP.TRC(SCCGWA, WS_AU2_BR);
        CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
        if (WS_AITHIS_FLG == 'Y') {
            AICPZMIB.FLG = 'Y';
        }
        while (WS_AITHIS_FLG != 'N') {
            CEP.TRC(SCCGWA, AIRMIB.BAL_CHK);
            CEP.TRC(SCCGWA, AIRMIB.CBAL);
            CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
            CEP.TRC(SCCGWA, AIRMIB.AC_NO);
            R000_DATA_OUTPUT();
            T000_READNEXT_AITMIB();
        }
        T000_ENDBR_AITMIB();
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        OUT_OCCURS = new AICPZMIB_OUT_OCCURS();
        AICPZMIB.OUT_DATA.OUT_OCCURS.add(OUT_OCCURS);
        AICPZMIB.CNT = AICPZMIB.CNT + 1;
        OUT_OCCURS.BAL_CHK = AIRMIB.BAL_CHK;
        OUT_OCCURS.CBAL = AIRMIB.CBAL;
        OUT_OCCURS.GL_BOOK = AIRMIB.KEY.GL_BOOK;
        OUT_OCCURS.AC_NO = AIRMIB.AC_NO;
        OUT_OCCURS.CCY = AIRMIB.KEY.CCY;
    }
    public void T000_STARTBR_AITMIB_1() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "( BAL_CHK = 'D' "
            + "OR BAL_CHK = :WS_EOM "
            + "OR BAL_CHK = :WS_EOY ) "
            + "AND CBAL < > 0";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_AITMIB_2() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "( BAL_CHK = 'D' "
            + "OR BAL_CHK = :WS_EOM "
            + "OR BAL_CHK = :WS_EOY ) "
            + "AND CBAL < > 0 "
            + "AND BR = :AIRMIB.KEY.BR";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void S000_CALL_BPZPCKEM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-END-MON", BPCOCKEM);
        CEP.TRC(SCCGWA, BPCOCKEM.RC);
        if (BPCOCKEM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCKEM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READNEXT_AITMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRMIB, this, AITMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AITHIS_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AITHIS_FLG = 'Y';
        }
    }
    public void T000_ENDBR_AITMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITMIB_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICPZMIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICPZMIB = ");
            CEP.TRC(SCCGWA, AICPZMIB);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
