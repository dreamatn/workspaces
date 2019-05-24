package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZUACR {
    DBParm CITACR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CIA08";
    char WS_CHK_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICFA08 CICFA08 = new CICFA08();
    CIRACR CIRACR = new CIRACR();
    CIRACR CIRACRO = new CIRACR();
    CIRACR CIRACRN = new CIRACR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICUACR CICUACR;
    public void MP(SCCGWA SCCGWA, CICUACR CICUACR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICUACR = CICUACR;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZUACR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICUACR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_BROWSE_ACR_INF();
        if (pgmRtn) return;
    }
    public void B010_BROWSE_ACR_INF() throws IOException,SQLException,Exception {
        if (CICUACR.DATA.OPR_TYP == '1') {
            B021_SIGN_ON();
            if (pgmRtn) return;
        } else if (CICUACR.DATA.OPR_TYP == '2') {
            B022_SIGN_OFF();
            if (pgmRtn) return;
        } else if (CICUACR.DATA.OPR_TYP == '3') {
            B023_BROWSE_ACR();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICUACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B021_SIGN_ON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICUACR.DATA.AGR_NO;
        T000_READ_CITACR_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
        CIRACR.SMS_FLG = 'Y';
        T000_REWRITE_CITACR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        CICFA08.AGR_NO = CIRACR.KEY.AGR_NO;
        CICFA08.SIG_FLG = CIRACR.SMS_FLG;
        B024_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B022_SIGN_OFF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICUACR.DATA.AGR_NO;
        T000_READ_CITACR_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
        CIRACR.SMS_FLG = 'N';
        T000_REWRITE_CITACR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        CICFA08.AGR_NO = CIRACR.KEY.AGR_NO;
        CICFA08.SIG_FLG = CIRACR.SMS_FLG;
        B024_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B023_BROWSE_ACR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICUACR.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        CICFA08.AGR_NO = CIRACR.KEY.AGR_NO;
        CICFA08.SIG_FLG = CIRACR.SMS_FLG;
        B024_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B024_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFA08;
        SCCFMT.DATA_LEN = 33;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_WRT_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRACR";
        BPCPNHIS.INFO.FMT_ID_LEN = 181;
        BPCPNHIS.INFO.CI_NO = CIRACR.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACRO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACRN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICUACR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR_UPD() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.upd = true;
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICUACR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.REWRITE(SCCGWA, CIRACR, CITACR_RD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
