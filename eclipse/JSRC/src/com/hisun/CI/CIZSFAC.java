package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSFAC {
    int BAS_CI_NM_LEN;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    brParm CITACAC_BR = new brParm();
    brParm CITACR_BR = new brParm();
    DBParm CITACR_RD;
    String K_CITIZEN_CARD = "1203010101";
    int WS_FIRST_NUM = 0;
    int WS_SECOND_NUM = 0;
    int WS_THIRD_NUM = 0;
    int WS_HOLD_NUM = 0;
    int WS_NO_ACT_NUM = 0;
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
    CIRACAC CIRACAC = new CIRACAC();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICPCDL CICPCDL = new CICPCDL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSFAC CICSFAC;
    public void MP(SCCGWA SCCGWA, CICSFAC CICSFAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSFAC = CICSFAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZSFAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSFAC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_COUNT_FIRST_NUM();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSFAC.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICSFAC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSFAC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSFAC.DATA.CI_NM);
        if (CICSFAC.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSFAC.DATA.CI_NO;
            T000_READ_CITBAS();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
            }
        } else if (CICSFAC.DATA.ID_TYPE.trim().length() > 0 
                && CICSFAC.DATA.ID_NO.trim().length() > 0 
                && CICSFAC.DATA.CI_NM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.ID_TYPE = CICSFAC.DATA.ID_TYPE;
            CIRBAS.ID_NO = CICSFAC.DATA.ID_NO;
            CIRBAS.CI_NM = CICSFAC.DATA.CI_NM;
            BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
            T000_READ_CITBAS_BY_IDNM();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
            }
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_COUNT_FIRST_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, CICPCDL);
        BPCPRMR.FUNC = ' ';
        BPRPRMT.KEY.TYP = "CIACL";
        BPRPRMT.KEY.CD = "05";
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPCDL);
        CEP.TRC(SCCGWA, CICPCDL);
        CEP.TRC(SCCGWA, CICPCDL.NUM);
        CEP.TRC(SCCGWA, CICPCDL.NUM);
        WS_CITIZEN_FLG = CICPCDL.CITIZEN_FLG;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
        WS_VIL_TYP = BPCPQORG.VIL_TYP;
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITACAC_BY_CI();
        T000_READNEXT_CITACAC();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
            CEP.TRC(SCCGWA, CIRACAC.ACAC_CTL);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = CIRACAC.OPN_BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
            if (BPCPQORG.VIL_TYP.equalsIgnoreCase(WS_VIL_TYP)) {
                IBS.init(SCCGWA, CIRACR);
                IBS.CPY2CLS(SCCGWA, CIRACAC.AGR_NO, CIRACR);
                T000_READ_CITACR();
                CEP.TRC(SCCGWA, WS_CITIZEN_FLG);
                CEP.TRC(SCCGWA, CIRACR.PROD_CD);
                if (WS_CITIZEN_FLG == 'Y' 
                    || !CIRACR.PROD_CD.equalsIgnoreCase(K_CITIZEN_CARD)) {
                        if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
                        JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
                    if (CIRACAC.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_FIRST_NUM = WS_FIRST_NUM + 1;
                        CEP.TRC(SCCGWA, WS_FIRST_NUM);
                        IBS.init(SCCGWA, DDCIQBAL);
                        DDCIQBAL.DATA.AC = CIRACAC.AGR_NO;
                        DDCIQBAL.DATA.CCY = CIRACAC.CCY;
                        DDCIQBAL.DATA.CCY_TYPE = CIRACAC.CR_FLG;
                        S000_CALL_DDZIQBAL();
                        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
                        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_HOLD_NUM = WS_HOLD_NUM + 1;
                            CEP.TRC(SCCGWA, WS_HOLD_NUM);
                        }
                        if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
                        JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
                    } else if (CIRACAC.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("2")) {
                        WS_SECOND_NUM = WS_SECOND_NUM + 1;
                        CEP.TRC(SCCGWA, WS_SECOND_NUM);
                        if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
                        JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
                    } else if (CIRACAC.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("3")) {
                        WS_THIRD_NUM = WS_THIRD_NUM + 1;
                        CEP.TRC(SCCGWA, WS_THIRD_NUM);
                    } else {
                    }
                }
            }
            T000_READNEXT_CITACAC();
        }
        T000_ENDBR_CITACAC();
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITACR_BY_CI();
        T000_READNEXT_CITACR();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            CEP.TRC(SCCGWA, CIRACR.ENTY_TYP);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = CIRACR.OPN_BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
            if (BPCPQORG.VIL_TYP.equalsIgnoreCase(WS_VIL_TYP)) {
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = CIRACR.KEY.AGR_NO;
                S000_CALL_DCZUCINF();
                CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
                if (DCCUCINF.CARD_STS == '2') {
                    WS_NO_ACT_NUM = WS_NO_ACT_NUM + 1;
                }
                CEP.TRC(SCCGWA, WS_NO_ACT_NUM);
            }
            T000_READNEXT_CITACR();
        }
        T000_ENDBR_CITACR();
        CICSFAC.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICSFAC.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICSFAC.DATA.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICSFAC.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICSFAC.DATA.FIRST_NUM = WS_FIRST_NUM;
        CICSFAC.DATA.SECOND_NUM = WS_SECOND_NUM;
        CICSFAC.DATA.THIRD_NUM = WS_THIRD_NUM;
        CICSFAC.DATA.HOLD_NUM = WS_HOLD_NUM;
        CICSFAC.DATA.NO_ACT_NUM = WS_NO_ACT_NUM;
        CEP.TRC(SCCGWA, CICSFAC.DATA.FIRST_NUM);
        CEP.TRC(SCCGWA, CICSFAC.DATA.HOLD_NUM);
        CEP.TRC(SCCGWA, CICSFAC.DATA.NO_ACT_NUM);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF, true);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
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
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE , ID_NO , CI_NM";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_STARTBR_CITACAC_BY_CI() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "CI_NO";
        CITACAC_BR.rp.where = "ACAC_STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void T000_STARTBR_CITACR_BY_CI() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "ENTY_TYP = '2' "
            + "AND STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
