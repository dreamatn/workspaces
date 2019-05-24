package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZIDNM {
    DBParm CITFDM_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS       ";
    String K_HIS_RMK = "CI ID INFO        ";
    String K_HIS_CPY = "CIRID";
    String K_TBL_CNT = "CITID";
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI003";
    int K_MAX_ROW = 30;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char WS_ID_FLG = ' ';
    char WS_NAM_FLG = ' ';
    char WS_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CICMID CICMID = new CICMID();
    CICMNAM CICMNAM = new CICMNAM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRFDM CIRFDM0 = new CIRFDM();
    CICOIDNM CICOIDNM = new CICOIDNM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICIDNM CICIDNM;
    public void MP(SCCGWA SCCGWA, CICIDNM CICIDNM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICIDNM = CICIDNM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZIDNM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_FLAG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICIDNM.DATA.ID_TYPE.trim().length() > 0 
            && CICIDNM.DATA.ID_NO.trim().length() > 0) {
            B020_MODIFY_ID_PROC();
            if (pgmRtn) return;
        }
        if (CICIDNM.DATA.NM_TYPE.trim().length() > 0 
            && CICIDNM.DATA.CI_NM.trim().length() > 0) {
            B030_MODIFY_NM_PROC();
            if (pgmRtn) return;
        }
        if (CICIDNM.DATA.FIN_TYP.trim().length() > 0 
            || CICIDNM.DATA.CORG.trim().length() > 0) {
            B040_MODIFY_FDM_PROC();
            if (pgmRtn) return;
        }
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_MODIFY_ID_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMID);
        CICMID.DATA.CI_NO = CICIDNM.DATA.CI_NO;
        CICMID.DATA.SINGLE_DATA.S_ID_TYPE = CICIDNM.DATA.ID_TYPE;
        CICMID.DATA.SINGLE_DATA.S_ID_NO = CICIDNM.DATA.ID_NO;
        CICMID.DATA.SINGLE_DATA.S_DESC = CICIDNM.DATA.REMARK;
        CICMID.DATA.SINGLE_DATA.S_OPEN = 'Y';
        CICMID.DATA.SINGLE_DATA.S_ID_RGN = CICIDNM.DATA.ID_RGN;
        CICMID.DATA.SINGLE_DATA.S_EXP_DT = CICIDNM.DATA.EXP_DT;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CICIDNM.DATA.CI_NO;
        CIRID.KEY.ID_TYPE = CICIDNM.DATA.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        if (WS_ID_FLG == 'Y') {
            CICMID.FUNC = 'M';
        } else {
            CICMID.FUNC = 'A';
            CICMID.CTLW_1 = 'O';
        }
        S000_LINK_CIZMID();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_NM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMNAM);
        CICMNAM.DATA.CI_NO = CICIDNM.DATA.CI_NO;
        CICMNAM.DATA.SINGLE_DATA.S_NM_TYPE = CICIDNM.DATA.NM_TYPE;
        CICMNAM.DATA.SINGLE_DATA.S_CI_NM = CICIDNM.DATA.CI_NM;
        CICMNAM.DATA.SINGLE_DATA.S_OPEN = 'Y';
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CICIDNM.DATA.CI_NO;
        CIRNAM.KEY.NM_TYPE = CICIDNM.DATA.NM_TYPE;
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (WS_NAM_FLG == 'Y') {
            CICMNAM.FUNC = 'M';
        } else {
            CICMNAM.FUNC = 'A';
            CICMNAM.CTLW_1 = 'O';
        }
        S000_LINK_CIZMNAM();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_FDM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFDM);
        IBS.init(SCCGWA, CICOIDNM);
        CIRFDM.KEY.CI_NO = CICIDNM.DATA.CI_NO;
        T000_READ_CITFDM_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST);
        }
        CIRFDM.FIN_TYPE = CICIDNM.DATA.FIN_TYP;
        CIRFDM.ORG_TYPE = CICIDNM.DATA.CORG;
        T000_REWRITE_CITFDM();
        if (pgmRtn) return;
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOIDNM);
        CICOIDNM.CI_NO = CICIDNM.DATA.CI_NO;
        CICOIDNM.ID_TYP = CICIDNM.DATA.ID_TYPE;
        CICOIDNM.ID_NO = CICIDNM.DATA.ID_NO;
        CICOIDNM.ID_RGN = CICIDNM.DATA.ID_RGN;
        CICOIDNM.ID_EXPDT = CICIDNM.DATA.EXP_DT;
        CICOIDNM.REMARK = CICIDNM.DATA.REMARK;
        CICOIDNM.CI_NM1 = CICIDNM.DATA.CI_NM;
        CICOIDNM.FIN_TYP = CICIDNM.DATA.FIN_TYP;
        CICOIDNM.CORG = CICIDNM.DATA.CORG;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        SCCFMT.DATA_PTR = CICOIDNM;
        SCCFMT.DATA_LEN = 483;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITFDM_UPD() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        CITFDM_RD.upd = true;
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_REWRITE_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.REWRITE(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_READ_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.col = "CI_NO";
        IBS.READ(SCCGWA, CIRID, CITID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ID_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ID_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITID";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        CITNAM_RD.col = "CI_NO";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_NAM_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_NAM_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITNAM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_LINK_CIZMID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ID", CICMID, true);
    }
    public void S000_LINK_CIZMNAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-NAM", CICMNAM, true);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICIDNM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICIDNM=");
            CEP.TRC(SCCGWA, CICIDNM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
