package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSMDAC {
    DBParm DCTIAACR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_COL = 99;
    String K_OUT_FMT = "DCA04";
    String K_HIS_CPB_NAME = "DCCSMDAC";
    String K_HIS_REMARKS = "MOD DEFAULT AC";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCOMDAC DCCOMDAC = new DCCOMDAC();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSMDAC DCCSMDAC;
    public void MP(SCCGWA SCCGWA, DCCSMDAC DCCSMDAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSMDAC = DCCSMDAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSMDAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B015_CHK_MSTR_PROC();
        if (pgmRtn) return;
        B020_MOD_DEFAULT_PROCESS();
        if (pgmRtn) return;
        B025_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
        B090_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSMDAC.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IA_AC_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DCCSMDAC.NEW_SEQ == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.SMDAC_NEW_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DCCSMDAC.OLD_SEQ == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.SMDAC_OLD_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B015_CHK_MSTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTR);
        DCCIMSTR.KEY.VIA_AC = DCCSMDAC.AC;
        CEP.TRC(SCCGWA, DCCIMSTR.KEY.VIA_AC);
        S000_CALL_DCZIMSTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCIMSTR.DATA.PRD_TYPE);
        if (!DCCIMSTR.DATA.PRD_TYPE.equalsIgnoreCase("07")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VA_PRD_TYPE_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MOD_DEFAULT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCCSMDAC.AC;
        DCRIAACR.KEY.SEQ = DCCSMDAC.OLD_SEQ;
        CEP.TRC(SCCGWA, DCCSMDAC.OLD_SEQ);
        T00_READUP_DCTIAACR();
        if (pgmRtn) return;
        if (DCRIAACR.DEFAULT_FLG != 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_DFT_SUB_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRIAACR.DEFAULT_FLG = 'N';
        DCRIAACR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T00_UPDATE_DCTIAACR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCCSMDAC.AC;
        DCRIAACR.KEY.SEQ = DCCSMDAC.NEW_SEQ;
        CEP.TRC(SCCGWA, DCCSMDAC.NEW_SEQ);
        T00_READUP_DCTIAACR();
        if (pgmRtn) return;
        if (!DCRIAACR.FRM_APP.equalsIgnoreCase("DD")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DEFAULT_DD_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRIAACR.ACCR_FLG != '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IA_AC_ALR_CAN_RL;
        }
        DCRIAACR.DEFAULT_FLG = 'Y';
        DCRIAACR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T00_UPDATE_DCTIAACR();
        if (pgmRtn) return;
    }
    public void B025_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DCCSMDAC.AC;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOMDAC);
        DCCOMDAC.VIA_AC = DCRIAACR.KEY.VIA_AC;
        DCCOMDAC.SEQ = DCRIAACR.KEY.SEQ;
        DCCOMDAC.SUB_AC = DCRIAACR.SUB_AC;
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "FORMAT OUTPUT");
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = DCCOMDAC;
        SCCFMT.DATA_LEN = 70;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T00_READUP_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.upd = true;
        IBS.READ(SCCGWA, DCRIAACR, DCTIAACR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACR_RCD_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIAACR ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_UPDATE_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        IBS.REWRITE(SCCGWA, DCRIAACR, DCTIAACR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTIAACR ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
