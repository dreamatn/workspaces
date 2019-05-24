package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZICCYM {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    int WS_HLD_NO = 0;
    short WS_STS_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCCY DDRCCYO = new DDRCCY();
    SCCGWA SCCGWA;
    DDCICCYM DDCICCYM;
    public void MP(SCCGWA SCCGWA, DDCICCYM DDCICCYM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCICCYM = DDCICCYM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZICCYM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDCICCYM.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        DDCICCYM.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCICCYM.TX_TYPE == 'I') {
            B010_INQ_CCY_INF_PROC();
        } else if (DDCICCYM.TX_TYPE == 'U') {
            B030_UPD_CCY_INF_PROC();
        } else if (DDCICCYM.TX_TYPE == 'S') {
            B050_SET_CCY_STS_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCICCYM.TX_TYPE + ")";
        }
    }
    public void B010_INQ_CCY_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCICCYM.DATA.AC_NO;
        T000_READ_DDTCCY();
        IBS.CLONE(SCCGWA, DDRCCY, DDRCCYO);
        R000_TRANS_DATA_OUT();
    }
    public void B030_UPD_CCY_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        R000_R_UPD_CCY_PROC();
        IBS.CLONE(SCCGWA, DDRCCY, DDRCCYO);
        R000_CHECK_CCY_STUS();
        T000_REWRITE_DDTCCY();
        R000_TRANS_DATA_OUT();
    }
    public void B050_SET_CCY_STS_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        R000_R_UPD_CCY_PROC();
        IBS.CLONE(SCCGWA, DDRCCY, DDRCCYO);
        R000_CHECK_CCY_STUS();
        if (DDCICCYM.DATA.STS_CD.trim().length() == 0) WS_STS_NO = 0;
        else WS_STS_NO = Short.parseShort(DDCICCYM.DATA.STS_CD);
        CEP.TRC(SCCGWA, DDCICCYM.DATA.SET_FLG);
        if (DDCICCYM.DATA.SET_FLG == 'O') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, WS_STS_NO - 1) + "1" + DDRCCY.STS_WORD.substring(WS_STS_NO + 1 - 1);
        } else {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, WS_STS_NO - 1) + "0" + DDRCCY.STS_WORD.substring(WS_STS_NO + 1 - 1);
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_DDTCCY();
        R000_TRANS_DATA_OUT();
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCICCYM.DATA.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDCICCYM.TX_TYPE == 'S') {
            if (DDCICCYM.DATA.STS_CD.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_STS_NO_M_INPUT);
            }
            if ((DDCICCYM.DATA.STS_CD.compareTo("00") < 0) 
                || (DDCICCYM.DATA.STS_CD.compareTo("99") > 0)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_NO_INVALID);
            }
            if ((DDCICCYM.DATA.SET_FLG != 'O') 
                && (DDCICCYM.DATA.SET_FLG != 'F')) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SET_FLG_INVALID);
            }
        }
    }
    public void R000_R_UPD_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCICCYM.DATA.AC_NO;
        T000_READUPD_DDTCCY();
    }
    public void R000_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
    }
    public void R000_CHECK_CCY_STUS() throws IOException,SQLException,Exception {
        if (DDRCCY.STS == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_CLEARED);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        }
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCICCYM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCICCYM=");
            CEP.TRC(SCCGWA, DDCICCYM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
