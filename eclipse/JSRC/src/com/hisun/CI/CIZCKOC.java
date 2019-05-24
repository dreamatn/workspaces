package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZCKOC {
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITLS2_RD;
    brParm CITAGENT_BR = new brParm();
    DBParm CITACR_RD;
    brParm CITACR_BR = new brParm();
    boolean pgmRtn = false;
    int WS_CI_NUM = 0;
    int WS_AGENT_NUM = 0;
    int WS_CI_COUNT = 0;
    int WS_AGENT_COUNT = 0;
    String WS_VIL_TYP = " ";
    char WS_CITIZEN_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRAGENT CIRAGENT = new CIRAGENT();
    CIRLS2 CIRLS2 = new CIRLS2();
    CICPCDL CICPCDL = new CICPCDL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCKOC CICCKOC;
    public void MP(SCCGWA SCCGWA, CICCKOC CICCKOC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCKOC = CICCKOC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZCKOC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCKOC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQUIRE_OPEN_BR();
        if (pgmRtn) return;
        if (CICCKOC.DATA.AGENT_FLG == 'Y') {
            B030_GET_AGENT_LMT_NUM();
            if (pgmRtn) return;
            B040_CHECK_AGENT_OPAC();
            if (pgmRtn) return;
        }
        B050_GET_CI_LMT_NUM();
        if (pgmRtn) return;
        B060_CHECK_CI_OPAC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCKOC.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICCKOC.DATA.OPEN_BR);
        if (CICCKOC.DATA.CI_NO.trim().length() == 0 
            || CICCKOC.DATA.OPEN_BR == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICCKOC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICCKOC.DATA.AGENT_FLG);
        CEP.TRC(SCCGWA, CICCKOC.DATA.AGENT_ID_TYP);
        CEP.TRC(SCCGWA, CICCKOC.DATA.AGENT_ID_NO);
        CEP.TRC(SCCGWA, CICCKOC.DATA.AGENT_NAME);
        if (CICCKOC.DATA.AGENT_FLG == 'Y' 
            && (CICCKOC.DATA.AGENT_ID_TYP.trim().length() == 0 
            || CICCKOC.DATA.AGENT_ID_NO.trim().length() == 0 
            || CICCKOC.DATA.AGENT_NAME.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "LACK OF AGENT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGENT_INF_MUST_INPUT, CICCKOC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUIRE_OPEN_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = CICCKOC.DATA.OPEN_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_VIL_TYP = BPCPQORG.VIL_TYP;
    }
    public void B030_GET_AGENT_LMT_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLS2);
        CIRLS2.KEY.LST_CD = "JGL";
        CIRLS2.KEY.ID_TYPE = CICCKOC.DATA.AGENT_ID_TYP;
        CIRLS2.KEY.ID_NO = CICCKOC.DATA.AGENT_ID_NO;
        CIRLS2.KEY.CI_CNM = CICCKOC.DATA.AGENT_NAME;
        T000_READ_CITLS2_FOR_JGL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, CICPCDL);
        BPCPRMR.FUNC = ' ';
        BPRPRMT.KEY.TYP = "CIACL";
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPRPRMT.KEY.CD = "04";
        } else {
            BPRPRMT.KEY.CD = "03";
        }
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPCDL);
        CEP.TRC(SCCGWA, CICPCDL.NUM);
        WS_AGENT_NUM = CICPCDL.NUM;
        WS_CITIZEN_FLG = CICPCDL.CITIZEN_FLG;
    }
    public void B040_CHECK_AGENT_OPAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGENT);
        CIRAGENT.ID_NO = CICCKOC.DATA.AGENT_ID_NO;
        CIRAGENT.ID_TYP = CICCKOC.DATA.AGENT_ID_TYP;
        CIRAGENT.CI_NM = CICCKOC.DATA.AGENT_NAME;
        CIRAGENT.AGENT_TP = "01";
        T000_STARTBR_CITAGENT();
        if (pgmRtn) return;
        T000_READNEXT_CITAGENT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRAGENT.OUT_AC;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (CIRACR.STS == '0' 
                && !CIRACR.CNTRCT_TYP.equalsIgnoreCase("298")) {
                CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
                CEP.TRC(SCCGWA, CIRACR.OPN_BR);
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CIRACR.OPN_BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.VIL_TYP.equalsIgnoreCase(WS_VIL_TYP)) {
                    WS_AGENT_COUNT = WS_AGENT_COUNT + 1;
                }
            }
            T000_READNEXT_CITAGENT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGENT();
        if (pgmRtn) return;
        if (WS_AGENT_COUNT >= WS_AGENT_NUM) {
            CEP.TRC(SCCGWA, "AGENT OPEN CARD OVER LIMIT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGENT_OPCA_OVER_LMT, CICCKOC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_GET_CI_LMT_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICCKOC.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRLS2);
        CIRLS2.KEY.LST_CD = "JGL";
        CIRLS2.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        CIRLS2.KEY.ID_NO = CIRBAS.ID_NO;
        CIRLS2.KEY.CI_CNM = CIRBAS.CI_NM;
        T000_READ_CITLS2_FOR_JGL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, CICPCDL);
        BPCPRMR.FUNC = ' ';
        BPRPRMT.KEY.TYP = "CIACL";
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPRPRMT.KEY.CD = "02";
        } else {
            BPRPRMT.KEY.CD = "01";
        }
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPCDL);
        CEP.TRC(SCCGWA, CICPCDL.NUM);
        WS_CI_NUM = CICPCDL.NUM;
        WS_CITIZEN_FLG = CICPCDL.CITIZEN_FLG;
    }
    public void B060_CHECK_CI_OPAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICCKOC.DATA.CI_NO;
        if (CICCKOC.DATA.ENTY_TYP5_FLG == 'Y') {
            T000_STARTBR_CITACR_ENTY_2AND5();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITACR();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (CIRACR.STS == '0' 
                && !CIRACR.CNTRCT_TYP.equalsIgnoreCase("298")) {
                CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
                CEP.TRC(SCCGWA, CIRACR.OPN_BR);
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CIRACR.OPN_BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.VIL_TYP.equalsIgnoreCase(WS_VIL_TYP)) {
                    WS_CI_COUNT = WS_CI_COUNT + 1;
                }
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CI_COUNT);
        CEP.TRC(SCCGWA, WS_CI_NUM);
        if (WS_CI_COUNT >= WS_CI_NUM) {
            CEP.TRC(SCCGWA, "CI OPEN CARD OVER LIMIT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_OPEN_CARD_OVER_LIMIT, CICCKOC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCPRMR.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITLS2_FOR_JGL() throws IOException,SQLException,Exception {
        CITLS2_RD = new DBParm();
        CITLS2_RD.TableName = "CITLS2";
        CITLS2_RD.eqWhere = "LST_CD , ID_TYPE , ID_NO , CI_CNM";
        IBS.READ(SCCGWA, CIRLS2, CITLS2_RD);
    }
    public void T000_STARTBR_CITAGENT() throws IOException,SQLException,Exception {
        CITAGENT_BR.rp = new DBParm();
        CITAGENT_BR.rp.TableName = "CITAGENT";
        CITAGENT_BR.rp.eqWhere = "ID_NO , ID_TYP , CI_NM , AGENT_TP";
        IBS.STARTBR(SCCGWA, CIRAGENT, CITAGENT_BR);
    }
    public void T000_READNEXT_CITAGENT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGENT, this, CITAGENT_BR);
    }
    public void T000_ENDBR_CITAGENT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGENT_BR);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_STARTBR_CITACR() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "ENTY_TYP = '2' "
            + "AND STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_ENTY_2AND5() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "ENTY_TYP IN ( '2' , '5' ) "
            + "AND STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
