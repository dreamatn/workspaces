package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZCHPSW {
    int JIBS_tmp_int;
    DBParm TDTCMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    TDZCHPSW_WS_LIST WS_LIST = new TDZCHPSW_WS_LIST();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    TDRCMST TDRCMST = new TDRCMST();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCGWA SCCGWA;
    TDCCHPSW TDCCHPSW;
    public void MP(SCCGWA SCCGWA, TDCCHPSW TDCCHPSW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCCHPSW = TDCCHPSW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZCHPSW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCHPSW.AC);
        CEP.TRC(SCCGWA, TDCCHPSW.CHK_FLG);
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B200_UPDATE_PSW_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (TDCCHPSW.AC.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCCHPSW.CHK_FLG == ' ') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_UPDATE_PSW_PROC() throws IOException,SQLException,Exception {
        if (TDCCHPSW.CHK_FLG == 'Y') {
            B200_UPDATE_PSW_Y();
            if (pgmRtn) return;
        } else if (TDCCHPSW.CHK_FLG == 'N') {
            B200_UPDATE_PSW_N();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_UPDATE_PSW_Y() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCCHPSW.AC;
        T000_READUP_TDTCMST();
        if (pgmRtn) return;
        if (TDRCMST.ERR_NUM > 0) {
            TDRCMST.ERR_NUM = 0;
        }
        TDRCMST.OPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTCMST();
        if (pgmRtn) return;
    }
    public void B200_UPDATE_PSW_N() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCCHPSW.AC;
        T000_READUP_TDTCMST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.AC_DATE > TDRCMST.OPT_DT) {
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            TDRCMST.STSW = TDRCMST.STSW.substring(0, 4 - 1) + "0" + TDRCMST.STSW.substring(4 + 1 - 1);
            TDRCMST.ERR_NUM = 0;
        }
        TDRCMST.ERR_NUM = (short) (TDRCMST.ERR_NUM + 1);
        if (TDRCMST.ERR_NUM == 6) {
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            TDRCMST.STSW = TDRCMST.STSW.substring(0, 4 - 1) + "1" + TDRCMST.STSW.substring(4 + 1 - 1);
        }
        TDRCMST.OPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTCMST();
        if (pgmRtn) return;
    }
    public void T000_READUP_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
