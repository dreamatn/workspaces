package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DD.*;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUGBAL {
    brParm DDTCCY_BR = new brParm();
    brParm TDTSMST_BR = new brParm();
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    double WS_DD_BAL = 0;
    double WS_TD_BAL = 0;
    char WS_CCY_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRCCY DDRCCY = new DDRCCY();
    TDRSMST TDRSMST = new TDRSMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUGBAL DCCUGBAL;
    public void MP(SCCGWA SCCGWA, DCCUGBAL DCCUGBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUGBAL = DCCUGBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUGBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCUGBAL.I_DATA.FUNC == '1') {
            B001_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B002_GET_DD_BAL();
            if (pgmRtn) return;
            B003_GET_TD_BAL();
            if (pgmRtn) return;
            B004_DATA_OTPUT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCUGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCUGBAL.I_DATA.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_MISSING, DCCUGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCUGBAL.I_DATA.CCY.trim().length() == 0) {
            DCCUGBAL.I_DATA.CCY = "156";
        }
    }
    public void B002_GET_DD_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DCCUGBAL.I_DATA.AC_NO;
        DDRCCY.CCY = DCCUGBAL.I_DATA.CCY;
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_DD_BAL += DDRCCY.CURR_BAL;
            CEP.TRC(SCCGWA, WS_DD_BAL);
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DD_BAL);
    }
    public void B003_GET_TD_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = DCCUGBAL.I_DATA.AC_NO;
        TDRSMST.CCY = DCCUGBAL.I_DATA.CCY;
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_TD_BAL += TDRSMST.BAL;
            CEP.TRC(SCCGWA, WS_TD_BAL);
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TD_BAL);
    }
    public void B004_DATA_OTPUT() throws IOException,SQLException,Exception {
        DCCUGBAL.O_DATA.DD_BAL = WS_DD_BAL;
        DCCUGBAL.O_DATA.TD_BAL = WS_TD_BAL;
        DCCUGBAL.O_DATA.TOT_BAL = DCCUGBAL.O_DATA.DD_BAL + DCCUGBAL.O_DATA.TD_BAL;
        CEP.TRC(SCCGWA, DCCUGBAL.O_DATA.DD_BAL);
        CEP.TRC(SCCGWA, DCCUGBAL.O_DATA.TD_BAL);
        CEP.TRC(SCCGWA, DCCUGBAL.O_DATA.TOT_BAL);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND CCY = :TDRSMST.CCY";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
