package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSTZMS {
    DBParm TDTCALL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    TDRCALL TDRCALL = new TDRCALL();
    TDCHTZMS TDCNTZMS = new TDCHTZMS();
    TDCHTZMS TDCOTZMS = new TDCHTZMS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    TDCSTZMS TDCSTZMS;
    public void MP(SCCGWA SCCGWA, TDCSTZMS TDCSTZMS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSTZMS = TDCSTZMS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSTZMS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSTZMS.AC);
        CEP.TRC(SCCGWA, TDCSTZMS.CALL_NO);
        B010_UPD_CALL_STS();
        if (pgmRtn) return;
    }
    public void B010_UPD_CALL_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCALL);
        TDRCALL.KEY.ACO_AC = TDCSTZMS.AC;
        TDRCALL.KEY.CALL_NO = TDCSTZMS.CALL_NO;
        T000_READUP_TDTCALL();
        if (pgmRtn) return;
        R000_TRANS_OTZMS_DATA();
        if (pgmRtn) return;
        if (TDRCALL.CALL_STS == '0') {
            TDRCALL.CALL_STS = '3';
            TDRCALL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRCALL.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDCALL();
            if (pgmRtn) return;
            R000_TRANS_NTZMS_DATA();
            if (pgmRtn) return;
            B030_WRITE_NHIS();
            if (pgmRtn) return;
        }
    }
    public void B030_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "TDBSP06";
        BPCPNHIS.INFO.FMT_ID_LEN = 53;
        BPCPNHIS.INFO.TX_RMK = "AUTO MOD CALL STS";
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = TDCOTZMS;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCNTZMS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_OTZMS_DATA() throws IOException,SQLException,Exception {
        TDCOTZMS.AC = TDCSTZMS.AC;
        TDCOTZMS.CALL_NO = TDCSTZMS.CALL_NO;
        TDCOTZMS.CALL_STS = TDRCALL.CALL_STS;
        TDCOTZMS.UPDTBL_TLR = TDRCALL.UPD_TLT;
        TDCOTZMS.UPDTBL_DATE = TDRCALL.UPD_DATE;
    }
    public void R000_TRANS_NTZMS_DATA() throws IOException,SQLException,Exception {
        TDCNTZMS.AC = TDCSTZMS.AC;
        TDCNTZMS.CALL_NO = TDCSTZMS.CALL_NO;
        TDCNTZMS.CALL_STS = TDRCALL.CALL_STS;
        TDCNTZMS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDCNTZMS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_TDTCALL() throws IOException,SQLException,Exception {
        TDTCALL_RD = new DBParm();
        TDTCALL_RD.TableName = "TDTCALL";
        TDTCALL_RD.upd = true;
        IBS.READ(SCCGWA, TDRCALL, TDTCALL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDCALL() throws IOException,SQLException,Exception {
        TDTCALL_RD = new DBParm();
        TDTCALL_RD.TableName = "TDTCALL";
        IBS.REWRITE(SCCGWA, TDRCALL, TDTCALL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.TYPE = 'D';
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = IBS.CLS2CPY(SCCGWA, TDRCALL.KEY);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCALL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
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
