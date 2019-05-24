package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZMTTP {
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DDXXX";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCSCXJS";
    String K_HIS_REMARKS = "CHUXU ACC TRANS JSUAN ACC";
    String K_TBL_CDDAT = "DCTCDDAT";
    char WS_TBL_FLAG = ' ';
    String WS_ERR_MSG = " ";
    short WS_CCY_NOT_INT_BAL = 0;
    short WS_CCY_CURR_BAL = 0;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDRCCY DDRCCY = new DDRCCY();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDCHCXJS DDCHCXJSO = new DDCHCXJS();
    DDCHCXJS DDCHCXJSN = new DDCHCXJS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCMTTP DDCMTTP;
    public void MP(SCCGWA SCCGWA, DDCMTTP DDCMTTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCMTTP = DDCMTTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZMTTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_INIT_PROC();
        if (pgmRtn) return;
        B020_INIT_PROC();
        if (pgmRtn) return;
    }
    public void B010_INIT_PROC() throws IOException,SQLException,Exception {
        if (DDCMTTP.FUNC != '1' 
            && DDCMTTP.FUNC != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCMTTP.FUNC == '1') {
            B001_NEXT_PROC();
            if (pgmRtn) return;
        }
        if (DDCMTTP.FUNC == '2') {
            B002_NEXT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_NEXT_PROC() throws IOException,SQLException,Exception {
        if (DDCMTTP.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            C001_NEXT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B002_NEXT_PROC() throws IOException,SQLException,Exception {
        if (DDCMTTP.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            C002_NEXT_PROC();
            if (pgmRtn) return;
        }
    }
    public void C001_NEXT_PROC() throws IOException,SQLException,Exception {
        if (DDCMTTP.TAMT == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DDRCCY.KEY.AC = DDCMTTP.AC;
            T000_READ_UPT_DDTCCY();
            if (pgmRtn) return;
            T000_COMP_TAMT();
            if (pgmRtn) return;
            T000_COMP_TAMT1();
            if (pgmRtn) return;
        }
    }
    public void C002_NEXT_PROC() throws IOException,SQLException,Exception {
        if (DDCMTTP.TAMT == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DDRCCY.KEY.AC = DDCMTTP.AC;
            T000_READ_UPT_DDTCCY();
            if (pgmRtn) return;
            T001_COMP_TAMT();
            if (pgmRtn) return;
            T001_COMP_TAMT1();
            if (pgmRtn) return;
        }
    }
    public void B020_INIT_PROC() throws IOException,SQLException,Exception {
        if (DDRCCY.AC_TYPE != '6') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACTION_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void T000_READ_UPT_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_COMP_TAMT() throws IOException,SQLException,Exception {
        DDRCCY.CURR_BAL = DDRCCY.CURR_BAL + DDCMTTP.TAMT;
    }
    public void T000_COMP_TAMT1() throws IOException,SQLException,Exception {
        DDRCCY.NOT_INT_BAL = DDRCCY.NOT_INT_BAL - DDCMTTP.TAMT;
    }
    public void T001_COMP_TAMT() throws IOException,SQLException,Exception {
        DDRCCY.CURR_BAL = DDRCCY.CURR_BAL - DDCMTTP.TAMT;
        if (DDRCCY.CURR_BAL < 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
        }
    }
    public void T001_COMP_TAMT1() throws IOException,SQLException,Exception {
        DDRCCY.NOT_INT_BAL = DDRCCY.NOT_INT_BAL + DDCMTTP.TAMT;
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_REGIS_NOTFIN_HISTORY() throws IOException,SQLException,Exception {
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        R000_NFIN_TX_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_TRANS_NFHIS_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHCXJSO);
        DDCHCXJSO.CUS_AC = DDCMTTP.AC;
    }
    public void R000_TRANS_NFHIS_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHCXJSN);
        DDCHCXJSO.CUS_AC = DDCMTTP.AC;
    }
    public void R000_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DDCHCXJSO;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCHCXJSN;
        BPCPNHIS.INFO.AC = DDRCCY.KEY.AC;
        BPCPNHIS.INFO.TX_CD = "0115460";
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
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
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
