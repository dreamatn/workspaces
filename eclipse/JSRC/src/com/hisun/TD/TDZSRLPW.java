package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSRLPW {
    int JIBS_tmp_int;
    DBParm TDTCMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_INQ_MACT_FMT = "TD177";
    String WS_ERR_MSG = " ";
    TDZSRLPW_WS_LIST WS_LIST = new TDZSRLPW_WS_LIST();
    char WS_IAACR_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    DCRIAACR DCRIAACR = new DCRIAACR();
    SCCHMPW SCCHMPW = new SCCHMPW();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDRCMST TDRCMST = new TDRCMST();
    SCCGWA SCCGWA;
    TDCSRLPW TDCSRLPW;
    public void MP(SCCGWA SCCGWA, TDCSRLPW TDCSRLPW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSRLPW = TDCSRLPW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSRLPW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSRLPW.MAIN_AC);
        CEP.TRC(SCCGWA, TDCSRLPW.AC);
        CEP.TRC(SCCGWA, TDCSRLPW.AC_SEQ);
        B100_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B200_OUT_INF_PROC();
        if (pgmRtn) return;
        B300_WRI_NFIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B100_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        if (TDCSRLPW.MAIN_AC.trim().length() > 0) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCSRLPW.MAIN_AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            TDRCMST.STSW = TDRCMST.STSW.substring(0, 4 - 1) + "0" + TDRCMST.STSW.substring(4 + 1 - 1);
            if ("0".trim().length() == 0) TDRCMST.ERR_NUM = 0;
            else TDRCMST.ERR_NUM = Short.parseShort("0");
            TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTCMST();
            if (pgmRtn) return;
        }
    }
    public void B200_OUT_INF_PROC() throws IOException,SQLException,Exception {
        WS_LIST.WS_MAIN_AC = TDCSRLPW.MAIN_AC;
        WS_LIST.WS_AC_SEQ = TDCSRLPW.AC_SEQ;
        WS_LIST.WS_AC = TDCSRLPW.AC;
        WS_LIST.WS_BV_TYP = TDCSRLPW.BV_TYP;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_INQ_MACT_FMT;
        SCCFMT.DATA_PTR = WS_LIST;
        SCCFMT.DATA_LEN = 71;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.AC = TDCSRLPW.MAIN_AC;
        BPCPNHIS.INFO.AC_SEQ = TDCSRLPW.AC_SEQ;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND);
        }
    }
    public void T000_REWRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
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
