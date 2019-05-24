package com.hisun.CI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZCAGT {
    String JIBS_tmp_str[] = new String[10];
    brParm CITAGT_BR = new brParm();
    brParm CITACRL_BR = new brParm();
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "AGT INFO            ";
    String K_HIS_CPY = "CIRAGT";
    int WS_I = 0;
    String WS_AGR_NO = " ";
    char WS_ACR_FLG = ' ';
    char WS_AGT_FLG = ' ';
    char WS_CONTRAL_FLG = ' ';
    char WS_AGT_STS = ' ';
    String WS_AUTH_LVL = " ";
    CIZCAGT_REDEFINES14 REDEFINES14 = new CIZCAGT_REDEFINES14();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICSSCH CICSSCH = new CICSSCH();
    CICMADR CICMADR = new CICMADR();
    CICMCNT CICMCNT = new CICMCNT();
    CICMLMA CICMLMA = new CICMLMA();
    CIRACR CIRACR = new CIRACR();
    CIRACR CIRACRO = new CIRACR();
    CIRACR CIRACRN = new CIRACR();
    CIRAGT CIRAGT = new CIRAGT();
    CIRAGT CIRAGTN = new CIRAGT();
    CIRAGT CIRAGTO = new CIRAGT();
    CICPAGT CICPAGT = new CICPAGT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMAGT CICMAGT = new CICMAGT();
    CIRACRL CIRACRL = new CIRACRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCAGT CICCAGT;
    public void MP(SCCGWA SCCGWA, CICCAGT CICCAGT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCAGT = CICCAGT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZCAGT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRACR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (CICCAGT.FUNC == 'B'
            || CICCAGT.FUNC == ' ') {
            WS_AGR_NO = CICCAGT.DATA.AGR_NO;
            R000_CHECK_AGT_PROC();
            R000_CHECK_REL_AC_AGT_PROC();
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCAGT.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICCAGT.DATA.AGR_NO);
        if (CICCAGT.DATA.AGR_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AGR_NO_UNABLE_NULL, 23);
        }
    }
    public void R000_CHECK_AGT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.KEY.ENTY_NO = WS_AGR_NO;
        WS_AGT_STS = 'N';
        CIRAGT.AGT_STS = WS_AGT_STS;
        CEP.TRC(SCCGWA, CICCAGT.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, WS_AGR_NO);
        T000_STARTBR_CITAGT();
        T000_READNEXT_CITAGT();
        while (WS_AGT_FLG != 'N' 
            && WS_CONTRAL_FLG != 'E') {
            R000_CHECK_CLO_CTL_PROC();
            T000_READNEXT_CITAGT();
        }
        T000_ENDBR_CITAGT();
    }
    public void R000_CANCEL_AGT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.KEY.ENTY_NO = CICCAGT.DATA.AGR_NO;
        WS_AGT_STS = 'N';
        CIRAGT.AGT_STS = WS_AGT_STS;
        CEP.TRC(SCCGWA, CICCAGT.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICCAGT.DATA.AGR_NO);
        T000_STARTBR_CITAGT();
        T000_READNEXT_CITAGT();
        while (WS_AGT_FLG != 'N' 
            && WS_CONTRAL_FLG != 'E') {
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
            R000_CHECK_CLO_CTL_PROC();
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.DATA.AGT_NO = CIRAGT.KEY.AGT_NO;
            CICMAGT.DATA.CI_NO = CIRAGT.CI_NO;
            CICMAGT.FUNC = 'D';
            S000_CALL_CIZMAGT();
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
            T000_READNEXT_CITAGT();
        }
        T000_ENDBR_CITAGT();
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC();
    }
    public void R000_CHECK_CLO_CTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPAGT);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        BPRPRMT.KEY.TYP = "CIAGT";
        BPRPRMT.KEY.CD = CIRAGT.AGT_TYP;
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPAGT);
        CEP.TRC(SCCGWA, CICPAGT.CLO_CTL);
        if (CICPAGT.CLO_CTL == '1') {
            WS_CONTRAL_FLG = 'E';
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_CTL_IS_REJECT);
        }
    }
    public void R000_WRT_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.CI_NO = CICCAGT.DATA.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRAGTO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACRN;
        S000_CALL_BPZPNHIS();
    }
    public void R000_CHECK_REL_AC_AGT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = CICCAGT.DATA.AGR_NO;
        T000_STARTBR_CITACRL();
        T000_READNEXT_CITACRL();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_AGR_NO = CIRACRL.KEY.AC_NO;
            R000_CHECK_AGT_PROC();
            T000_READNEXT_CITACRL();
        }
        T000_ENDBR_CITACRL();
    }
    public void T000_STARTBR_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.where = "ENTY_NO = :CIRAGT.KEY.ENTY_NO "
            + "AND AGT_STS = :CIRAGT.AGT_STS";
        IBS.STARTBR(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_READNEXT_CITAGT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGT, this, CITAGT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AGT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_CITAGT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGT_BR);
    }
    public void T000_STARTBR_CITACRL() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO";
        CITACRL_BR.rp.where = "REL_STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_TYP_NOT_DEF);
            } else {
                CEP.ERR(SCCGWA, BPCPRMR.RC);
            }
        }
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMAGT.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICCAGT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICCAGT=");
            CEP.TRC(SCCGWA, CICCAGT);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
