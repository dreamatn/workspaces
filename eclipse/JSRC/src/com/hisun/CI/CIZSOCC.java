package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPARMC;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPRORGM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSOCC {
    boolean pgmRtn = false;
    short WS_I = 0;
    short WS_J = 0;
    short WS_REP_NUM = 0;
    short WS_LGP_NUM = 0;
    String WS_CI_NO = " ";
    String WS_RCI_NO = " ";
    String WS_ID_TYPE = " ";
    String WS_ID_NO = " ";
    String WS_NM_TP = " ";
    String WS_CI_NM = " ";
    int WS_ID_EXP_DT = 0;
    String WS_CRS_ADR = " ";
    String WS_MSG_INFO = " ";
    char WS_CRS_FLG = ' ';
    char WS_FATCA_FLG = ' ';
    char WS_EADR_FLG = ' ';
    char WS_BADR_FLG = ' ';
    char WS_OFCAD_FLG = ' ';
    char WS_COTR_FLG = ' ';
    char WS_BAS_FOUND_FLG = ' ';
    char WS_OPEN_ID_CHK_FLG = ' ';
    char WS_ID_EXP_FLG = ' ';
    char WS_INFO_LACK_FLG = ' ';
    String WS_TRT_VIL = " ";
    String WS_OPN_VIL = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIRCDM CIRCDM = new CIRCDM();
    CIRNAM CIRNAM = new CIRNAM();
    CIRID CIRID = new CIRID();
    CIRADR CIRADR = new CIRADR();
    CIRCNT CIRCNT = new CIRCNT();
    CIRRELN CIRRELN = new CIRRELN();
    CIRRISK CIRRISK = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    CIRRELT CIRRELT = new CIRRELT();
    CIRRREL CIRRREL = new CIRRREL();
    CICCINO CICCINO = new CICCINO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICOCINO CICOCINO = new CICOCINO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICUSIZE CICUSIZE = new CICUSIZE();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRORGM BPRORGM = new BPRORGM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSOCC CICSOCC;
    public void MP(SCCGWA SCCGWA, CICSOCC CICSOCC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSOCC = CICSOCC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSOCC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSOCC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CI_NO();
        if (pgmRtn) return;
        B030_WRITE_NORMAL_INFO();
        if (pgmRtn) return;
        B040_WRITE_ID_INFO();
        if (pgmRtn) return;
        B050_WRITE_NAM_INFO();
        if (pgmRtn) return;
        B060_WRITE_ADR_INFO();
        if (pgmRtn) return;
        B070_WRITE_CNT_INFO();
        if (pgmRtn) return;
        B080_WRITE_REL_INFO();
        if (pgmRtn) return;
        B090_WRITE_RISK_INFO();
        if (pgmRtn) return;
        B100_WRITE_HISTORY();
        if (pgmRtn) return;
        B120_OUTPUT_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSOCC.DATA.CI_TYP);
        if (CICSOCC.DATA.CI_TYP != '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户类型不符");
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.CI_ATTR);
        if (CICSOCC.DATA.CI_ATTR != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户性质不符");
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.SUB_TYP);
        if (CICSOCC.DATA.SUB_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "客户类型细分必须输入");
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.CAP_AMT);
        CEP.TRC(SCCGWA, CICSOCC.DATA.CAP_CCY);
        if ((CICSOCC.DATA.CAP_AMT != 0 
            && CICSOCC.DATA.CAP_CCY.trim().length() == 0) 
            || (CICSOCC.DATA.CAP_AMT == 0 
            && CICSOCC.DATA.CAP_CCY.trim().length() > 0)) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACTFD_ONE_NOTIN);
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSOCC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSOCC.DATA.CI_NM);
        if (CICSOCC.DATA.ID_TYPE.trim().length() == 0 
            || CICSOCC.DATA.ID_NO.trim().length() == 0 
            || CICSOCC.DATA.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "三要素必须输�?");
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.RESIDENT);
        CEP.TRC(SCCGWA, CICSOCC.DATA.HECO);
        CEP.TRC(SCCGWA, CICSOCC.DATA.INDUS1);
        CEP.TRC(SCCGWA, CICSOCC.DATA.SID_FLG);
        CEP.TRC(SCCGWA, CICSOCC.DATA.BUSN_SCP);
        CEP.TRC(SCCGWA, CICSOCC.DATA.REG_DT);
        CEP.TRC(SCCGWA, CICSOCC.DATA.REG_CCY);
        CEP.TRC(SCCGWA, CICSOCC.DATA.REG_AMT);
        CEP.TRC(SCCGWA, CICSOCC.DATA.NED_TYP);
        if (CICSOCC.DATA.RESIDENT == ' ' 
            || CICSOCC.DATA.HECO.trim().length() == 0 
            || CICSOCC.DATA.INDUS1.trim().length() == 0 
            || CICSOCC.DATA.SID_FLG == ' ' 
            || CICSOCC.DATA.BUSN_SCP.trim().length() == 0 
            || CICSOCC.DATA.REG_DT == 0 
            || CICSOCC.DATA.REG_CCY.trim().length() == 0 
            || CICSOCC.DATA.REG_AMT == 0 
            || CICSOCC.DATA.NED_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.ORGIN_TP);
        CEP.TRC(SCCGWA, CICSOCC.DATA.ORGIN1);
        if (CICSOCC.DATA.ORGIN_TP == '8' 
            && CICSOCC.DATA.ORGIN1.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSOCC.DATA.ORGIN1;
            T000_READ_CITBAS_BY_CINO();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICSOCC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSOCC.DATA.ID_NO;
        CIRBAS.CI_NM = CICSOCC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BAS_FOUND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_TRT_VIL = BPRORGM.VIL_TYP;
            CEP.TRC(SCCGWA, WS_TRT_VIL);
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = CIRBAS.OPN_BR;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_OPN_VIL = BPRORGM.VIL_TYP;
            CEP.TRC(SCCGWA, WS_OPN_VIL);
            if (!WS_TRT_VIL.equalsIgnoreCase(WS_OPN_VIL)) {
                WS_BAS_FOUND_FLG = 'N';
            } else {
                if (CIRBAS.CI_ATTR == '1' 
                    || CIRBAS.CI_ATTR == '3' 
                    || CIRBAS.CI_ATTR == '5') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_EXIST);
                }
                WS_BAS_FOUND_FLG = 'Y';
                WS_CI_NO = CIRBAS.KEY.CI_NO;
                if (CIRBAS.CI_ATTR == '6') {
                    R000_CLEAR_NOS_INFO();
                    if (pgmRtn) return;
                }
            }
        }
        if (CICSOCC.DATA.ID_TYPE.equalsIgnoreCase("23000")) {
            IBS.init(SCCGWA, CIRID);
            if (CICSOCC.DATA.ID_NO == null) CICSOCC.DATA.ID_NO = "";
            JIBS_tmp_int = CICSOCC.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSOCC.DATA.ID_NO += " ";
            if (CICSOCC.DATA.ID_NO == null) CICSOCC.DATA.ID_NO = "";
            JIBS_tmp_int = CICSOCC.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSOCC.DATA.ID_NO += " ";
            CIRID.ID_NO = CICSOCC.DATA.ID_NO + "-" + CICSOCC.DATA.ID_NO;
            if (CICSOCC.DATA.ID_NO == null) CICSOCC.DATA.ID_NO = "";
            JIBS_tmp_int = CICSOCC.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSOCC.DATA.ID_NO += " ";
            CEP.TRC(SCCGWA, CICSOCC.DATA.ID_NO.substring(9 - 1, 9 + 9 - 1));
            CEP.TRC(SCCGWA, CIRID.ID_NO);
            T000_READ_CITID_BY_IDNO();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_EXIST);
            }
        }
        for (WS_I = 1; WS_I <= 4; WS_I += 1) {
            CEP.TRC(SCCGWA, CICSOCC.DATA.ID_AREA[WS_I-1].ID_IDTYP);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ID_AREA[WS_I-1].ID_IDNO);
            if (CICSOCC.DATA.ID_AREA[WS_I-1].ID_IDTYP.equalsIgnoreCase(CICSOCC.DATA.ID_TYPE) 
                && CICSOCC.DATA.ID_AREA[WS_I-1].ID_IDNO.equalsIgnoreCase(CICSOCC.DATA.ID_NO)) {
                WS_OPEN_ID_CHK_FLG = 'Y';
                CICSOCC.DATA.ID_AREA[WS_I-1].ID_OPEN = 'Y';
                CEP.TRC(SCCGWA, CICSOCC.DATA.ID_AREA[WS_I-1].ID_EXPDT);
                WS_ID_EXP_DT = CICSOCC.DATA.ID_AREA[WS_I-1].ID_EXPDT;
                if (CICSOCC.DATA.ID_AREA[WS_I-1].ID_EXPDT < SCCGWA.COMM_AREA.AC_DATE 
                    && CICSOCC.DATA.ID_AREA[WS_I-1].ID_EXPDT != 0) {
                    CEP.TRC(SCCGWA, "ID EXP DATE < AC DATE");
                    WS_ID_EXP_FLG = 'Y';
                }
            } else {
                CICSOCC.DATA.ID_AREA[WS_I-1].ID_OPEN = 'N';
            }
        }
        CEP.TRC(SCCGWA, WS_OPEN_ID_CHK_FLG);
        if (WS_OPEN_ID_CHK_FLG != 'Y') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "�?户证件详细信息未输入");
        }
        CEP.TRC(SCCGWA, WS_ID_EXP_FLG);
        if (WS_ID_EXP_FLG == 'Y') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_IS_EXP);
        }
        for (WS_I = 1; WS_I <= 4 
            && WS_FATCA_FLG != 'Y'; WS_I += 1) {
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_TYPE);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_CNTY);
            if (CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.equalsIgnoreCase("210") 
                && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("USA")) {
                WS_FATCA_FLG = 'Y';
            } else {
                WS_FATCA_FLG = 'N';
            }
        }
        if (WS_FATCA_FLG == 'N') {
            for (WS_I = 1; WS_I <= 20 
                && WS_FATCA_FLG != 'Y'; WS_I += 1) {
                CEP.TRC(SCCGWA, CICSOCC.DATA.REL_AREA[WS_I-1].REL_RECD);
                CEP.TRC(SCCGWA, CICSOCC.DATA.REL_AREA[WS_I-1].REL_CNTY);
                if (CICSOCC.DATA.REL_AREA[WS_I-1].REL_RECD.equalsIgnoreCase("20202") 
                    && CICSOCC.DATA.REL_AREA[WS_I-1].REL_CNTY.equalsIgnoreCase("USA")) {
                    WS_FATCA_FLG = 'Y';
                } else {
                    WS_FATCA_FLG = 'N';
                }
            }
        }
        B011_FATCA_CHK_INFO();
        if (pgmRtn) return;
        B012_CRS_CHECK_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSOCC.DATA.RESIDENT);
        if (CICSOCC.DATA.RESIDENT != '1') {
            B013_MOD_CHECK_INFO();
            if (pgmRtn) return;
        }
        B014_MOD_CHECK_INFO();
        if (pgmRtn) return;
        B015_MOD_CHECK_INFO();
        if (pgmRtn) return;
        B016_LEG_PERS_CHECK();
        if (pgmRtn) return;
        if (CICSOCC.DATA.ID_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPARMC);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPRMR.FUNC = ' ';
            BPCPRMR.TYP = "PARMC";
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            BPCPRMR.CD = "CIIDO" + BPCPRMR.CD.substring(5);
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (CICSOCC.DATA.ID_TYPE == null) CICSOCC.DATA.ID_TYPE = "";
            JIBS_tmp_int = CICSOCC.DATA.ID_TYPE.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CICSOCC.DATA.ID_TYPE += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + CICSOCC.DATA.ID_TYPE + BPCPRMR.CD.substring(6 + 5 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
    }
    public void B011_FATCA_CHK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSOCC.DATA.FATCA_TP);
        CEP.TRC(SCCGWA, CICSOCC.DATA.TIN_NO);
        CEP.TRC(SCCGWA, CICSOCC.DATA.W8W9_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        if (WS_FATCA_FLG == 'N') {
            if (CICSOCC.DATA.FATCA_TP.trim().length() == 0 
                && CICSOCC.DATA.TIN_NO.trim().length() == 0) {
                CICSOCC.DATA.FATCA_TP = "NA00";
            }
        }
        if (WS_FATCA_FLG == 'Y') {
            if (CICSOCC.DATA.FATCA_TP.trim().length() == 0 
                && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300201")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_USA_REP_EST_RFIL);
            }
            if (!CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W900") 
                && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W901") 
                && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("AD00") 
                && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("RC00") 
                && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("RC01") 
                && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("PD00") 
                && CICSOCC.DATA.FATCA_TP.trim().length() > 0) {
                WS_MSG_INFO = "有FATCA表征，不可输�?" + CICSOCC.DATA.FATCA_TP;
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_TP_INP_ERR, WS_MSG_INFO);
            }
        }
        if (CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W800")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_TP_INP_ERR, "公司客户FATCA状�?�不可输入W800");
        }
        if ((CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("AD00") 
            || CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("RC00") 
            || CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("RC01") 
            || CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("PD00")) 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300201")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_ARPD);
        }
        if (!CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W800") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W802") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W803") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W804") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W805") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W806") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W807") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W808") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W809") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W810") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W900") 
            && !CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W901")) {
            if (CICSOCC.DATA.W8W9_DT != 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_SIGND_MUST_NOTIN);
            }
        } else {
            if (CICSOCC.DATA.W8W9_DT != 0) {
                if (CICSOCC.DATA.W8W9_DT > SCCGWA.COMM_AREA.AC_DATE) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_SIGND_NOGRT_ACD);
                }
            } else {
                CICSOCC.DATA.W8W9_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        if (CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W900") 
            || CICSOCC.DATA.FATCA_TP.equalsIgnoreCase("W901")) {
            if (CICSOCC.DATA.TIN_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_W9TIN_MUST_INPUT);
            }
        } else {
            if (CICSOCC.DATA.TIN_NO.trim().length() > 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_W9TIN_MUST_NOTIN);
            }
        }
    }
    public void B012_CRS_CHECK_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 2 
            && WS_CRS_FLG != 'N'; WS_I += 1) {
            WS_CRS_ADR = " ";
            if (WS_I == 1) {
                for (WS_J = 1; WS_J <= 4; WS_J += 1) {
                    if (CICSOCC.DATA.ADR_AREA[WS_J-1].ADR_TYPE.equalsIgnoreCase("210")) {
                        WS_CRS_ADR = CICSOCC.DATA.ADR_AREA[WS_J-1].ADR_CNTY;
                    }
                }
            } else {
                for (WS_J = 1; WS_J <= 4; WS_J += 1) {
                    if (CICSOCC.DATA.ADR_AREA[WS_J-1].ADR_TYPE.equalsIgnoreCase("240")) {
                        WS_CRS_ADR = CICSOCC.DATA.ADR_AREA[WS_J-1].ADR_CNTY;
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_CRS_ADR);
            if (WS_CRS_ADR.trim().length() > 0) {
                WS_CRS_FLG = 'N';
                for (WS_J = 1; WS_J <= 25 
                    && WS_CRS_FLG != 'Y'; WS_J += 1) {
                    CEP.TRC(SCCGWA, CICSOCC.DATA.CRS_AREA[WS_J-1].CRS_ADR);
                    if (CICSOCC.DATA.CRS_AREA[WS_J-1].CRS_ADR.equalsIgnoreCase(WS_CRS_ADR)) {
                        WS_CRS_FLG = 'Y';
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.CRS_TYPE);
        CEP.TRC(SCCGWA, CICSOCC.DATA.CRS_AREA[1-1].CRS_ADR);
        CEP.TRC(SCCGWA, CICSOCC.DATA.CRS_DESC);
        CEP.TRC(SCCGWA, CICSOCC.DATA.PROOF_DT);
        CEP.TRC(SCCGWA, CICSOCC.DATA.PROOF_CH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        if (CICSOCC.DATA.CRS_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "CRS状�?�为必输�?");
        }
        if (WS_CRS_FLG == 'N' 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300201")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_DESC_EMPW, "税务居住地没有CRS表征地址");
        }
        if (!CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("NA") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("P0") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("P1") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("R0") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("R1") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("EX") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("UD") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("N2") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("F1") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("F2") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("F3") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("LC") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("GV") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("IO") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("AN") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("PN") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("RT") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("ID")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INVAD_CRS_TP);
        }
        if ((CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("R0") 
            || CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("R1") 
            || CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("EX")) 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300201")) {
            WS_MSG_INFO = " ";
            WS_MSG_INFO = "CRS状�?�为" + CICSOCC.DATA.CRS_TYPE;
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_TP_EMPW, WS_MSG_INFO);
        }
        if (CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("RT") 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300201")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_TP_RT_PROOF, "请在备注中输入拒绝理�?");
        }
        if (!CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("NA") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("P0") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("RT") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("N2") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("R0") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("R1") 
            && !CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("EX")) {
            if (CICSOCC.DATA.CRS_AREA[1-1].CRS_ADR.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_ADR_ONE_INPUT);
            }
        }
        if ((CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("NA") 
            || CICSOCC.DATA.CRS_TYPE.equalsIgnoreCase("RT")) 
            && CICSOCC.DATA.CRS_DESC.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_DESC_INPUT, "CRS状�?�为NA/RT");
        }
        if (CICSOCC.DATA.CRS_DESC.trim().length() > 0 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300201")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_DESC_EMPW, "CRS状�?�原因备注不为空");
        }
        if (CICSOCC.DATA.PROOF_CH != 'B' 
            && CICSOCC.DATA.PROOF_CH != 'E' 
            && CICSOCC.DATA.PROOF_CH != 'P' 
            && CICSOCC.DATA.PROOF_CH != ' ') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INVAD_PROOF_CH);
        }
        if (CICSOCC.DATA.PROOF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_PROOFD_NOGRT_ACD);
        }
        for (WS_I = 1; WS_I <= 25 
            && CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_ADR.trim().length() != 0; WS_I += 1) {
            WS_REP_NUM = 0;
            CEP.TRC(SCCGWA, CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_ADR);
            CEP.TRC(SCCGWA, CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_DSNO);
            CEP.TRC(SCCGWA, CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDCD);
            CEP.TRC(SCCGWA, CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDRE);
            if (CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_ADR.trim().length() > 0 
                && CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_DSNO.trim().length() > 0 
                && CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != ' ') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DSNO_NDCD_ONE);
            }
            if (CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_DSNO.trim().length() == 0 
                && !CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_ADR.equalsIgnoreCase("CHN") 
                && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300201")) {
                WS_MSG_INFO = " ";
                WS_MSG_INFO = "税务居住地为" + CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_ADR;
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_DENO_IMCPL, WS_MSG_INFO);
            }
            if (CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_DSNO.trim().length() == 0 
                && (CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != 'A' 
                && CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != 'B' 
                && CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != 'C' 
                && CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != ' ')) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INVAD_CRS_NDCD);
            }
            if (CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDCD == 'B' 
                && CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDRE.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_NDRE_NEED_INPUT);
            }
            if (CICSOCC.DATA.CRS_AREA[WS_I-1].CRS_NDRE.trim().length() > 0 
                && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300201")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_NDRE_EMPW);
            }
        }
    }
    public void B013_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 4 
            && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_OPT != ' '; WS_I += 1) {
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_TYPE);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_CNTY);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L1);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L2);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L3);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L4);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L5);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L6);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L7);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_E2);
            CEP.TRC(SCCGWA, CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_NM);
            if (CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "地址类型必须输入");
            }
            if (CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "国家代码必须输入");
            }
            if ((CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L1.trim().length() > 0 
                && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L2.trim().length() > 0 
                && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_E2.trim().length() == 0) 
                || (CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L1.trim().length() == 0 
                && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L2.trim().length() == 0 
                && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_E2.trim().length() > 0)) {
            } else {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BHA_PAC_ONEIN);
            }
            if (CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L3.trim().length() == 0 
                && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L4.trim().length() == 0 
                && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L5.trim().length() == 0 
                && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L6.trim().length() == 0 
                && CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_L7.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CLFD_CNT_SPACE);
            }
            if (CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_NM.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "详细地址必须输入");
            }
            if (CICSOCC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.equalsIgnoreCase("230")) {
                WS_OFCAD_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.CI_ENM);
        if (CICSOCC.DATA.CI_ENM.trim().length() == 0) {
            WS_MSG_INFO = " ";
            WS_MSG_INFO = "非居民客户，" + "英文名称必输";
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, WS_MSG_INFO);
        }
        if (WS_OFCAD_FLG != 'Y') {
            WS_MSG_INFO = " ";
            WS_MSG_INFO = "非居民客户，" + "办公地址英文必输";
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, WS_MSG_INFO);
        }
    }
    public void B014_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSOCC.DATA.SCH_DT);
        if (CICSOCC.DATA.SCH_DT == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "查册日期必须输入");
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.HKC_TYP);
        if (CICSOCC.DATA.HKC_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "香港客户分类必须输入");
        }
        CEP.TRC(SCCGWA, CICSOCC.DATA.RESIDENT);
        if (CICSOCC.DATA.RESIDENT != '1') {
            for (WS_I = 1; WS_I <= 20 
                && CICSOCC.DATA.REL_AREA[WS_I-1].REL_OPT != ' '; WS_I += 1) {
                if (CICSOCC.DATA.REL_AREA[WS_I-1].REL_RECD.equalsIgnoreCase("20202")) {
                    if (CICSOCC.DATA.REL_AREA[WS_I-1].REL_NM1.trim().length() == 0 
                        || CICSOCC.DATA.REL_AREA[WS_I-1].REL_NM2.trim().length() == 0 
                        || CICSOCC.DATA.REL_AREA[WS_I-1].REL_RSDT == ' ' 
                        || CICSOCC.DATA.REL_AREA[WS_I-1].REL_BRCT.trim().length() == 0 
                        || CICSOCC.DATA.REL_AREA[WS_I-1].REL_FMCT.trim().length() == 0 
                        || CICSOCC.DATA.REL_AREA[WS_I-1].REL_FMAD.trim().length() == 0) {
                        CEP.TRC(SCCGWA, CICSOCC.DATA.REL_AREA[WS_I-1].REL_NM1);
                        CEP.TRC(SCCGWA, CICSOCC.DATA.REL_AREA[WS_I-1].REL_NM2);
                        CEP.TRC(SCCGWA, CICSOCC.DATA.REL_AREA[WS_I-1].REL_RSDT);
                        CEP.TRC(SCCGWA, CICSOCC.DATA.REL_AREA[WS_I-1].REL_BRCT);
                        CEP.TRC(SCCGWA, CICSOCC.DATA.REL_AREA[WS_I-1].REL_FMCT);
                        CEP.TRC(SCCGWA, CICSOCC.DATA.REL_AREA[WS_I-1].REL_FMAD);
                        WS_MSG_INFO = " ";
                        WS_MSG_INFO = "控制人姓、名�?" + "居民性质�?" + "英文地址 必输";
                        CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, WS_MSG_INFO);
                    }
                }
            }
        }
    }
    public void B015_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSOCC.DATA.INDUS2);
        if (CICSOCC.DATA.INDUS2.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "香港行业分类必须输入");
        }
    }
    public void B016_LEG_PERS_CHECK() throws IOException,SQLException,Exception {
        WS_LGP_NUM = 0;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            if (CICSOCC.DATA.REL_AREA[WS_I-1].REL_OPT == 'A') {
                if (CICSOCC.DATA.REL_AREA[WS_I-1].REL_RECD.equalsIgnoreCase("20101")) {
                    WS_LGP_NUM += 1;
                }
            }
        }
        CEP.TRC(SCCGWA, WS_LGP_NUM);
        if (WS_LGP_NUM > 1) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "法人代表录入重复");
        }
    }
    public void B020_GET_CI_NO() throws IOException,SQLException,Exception {
        if (WS_BAS_FOUND_FLG == 'N') {
            CEP.TRC(SCCGWA, "CALL CIZCINO");
            IBS.init(SCCGWA, CICCINO);
            S000_CALL_CIZCINO();
            if (pgmRtn) return;
            WS_CI_NO = CICCINO.DATA.CI_NO;
        }
        CEP.TRC(SCCGWA, WS_CI_NO);
    }
    public void B030_WRITE_NORMAL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRBASN);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        CIRBAS.CI_TYP = CICSOCC.DATA.CI_TYP;
        CIRBAS.CI_ATTR = CICSOCC.DATA.CI_ATTR;
        CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "1" + CIRBAS.STSW.substring(1);
        CIRBAS.IDE_STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        CEP.TRC(SCCGWA, CICSOCC.DATA.PL_FLG);
        CEP.TRC(SCCGWA, CICSOCC.DATA.PEA_FLG);
        CEP.TRC(SCCGWA, CICSOCC.DATA.GRE_FLG);
        CEP.TRC(SCCGWA, CICSOCC.DATA.CDG_EMP);
        if (CICSOCC.DATA.CDG_EMP == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 2 - 1) + "1" + CIRBAS.IDE_STSW.substring(2 + 1 - 1);
        }
        if (CICSOCC.DATA.PL_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 3 - 1) + "1" + CIRBAS.IDE_STSW.substring(3 + 1 - 1);
        }
        if (CICSOCC.DATA.PEA_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 4 - 1) + "1" + CIRBAS.IDE_STSW.substring(4 + 1 - 1);
        }
        if (CICSOCC.DATA.GRE_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 5 - 1) + "1" + CIRBAS.IDE_STSW.substring(5 + 1 - 1);
        }
        CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
        IBS.init(SCCGWA, CIRRELT);
        CIRRELT.RELT_CNM = CICSOCC.DATA.CI_NM;
        CIRRELT.RELT_ENM = CICSOCC.DATA.CI_ENM;
        T000_STARTBR_CITRELT_BY_NM();
        if (pgmRtn) return;
        T000_READNEXT_CITRELT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRRELT.RELT_TYPE);
            if (CIRRELT.RELT_TYPE.equalsIgnoreCase("01")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 11 - 1) + "1" + CIRBAS.IDE_STSW.substring(11 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("02")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 12 - 1) + "1" + CIRBAS.IDE_STSW.substring(12 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("03")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 13 - 1) + "1" + CIRBAS.IDE_STSW.substring(13 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("04")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 14 - 1) + "1" + CIRBAS.IDE_STSW.substring(14 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("05")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 15 - 1) + "1" + CIRBAS.IDE_STSW.substring(15 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("06")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 16 - 1) + "1" + CIRBAS.IDE_STSW.substring(16 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("07")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 17 - 1) + "1" + CIRBAS.IDE_STSW.substring(17 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("08")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 18 - 1) + "1" + CIRBAS.IDE_STSW.substring(18 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("09")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 19 - 1) + "1" + CIRBAS.IDE_STSW.substring(19 + 1 - 1);
            } else {
            }
            T000_READNEXT_CITRELT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITRELT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRRREL);
        CIRRREL.REL_CNM = CICSOCC.DATA.CI_NM;
        CIRRREL.REL_ENM = CICSOCC.DATA.CI_ENM;
        T000_STARTBR_CITRREL_BY_NM();
        if (pgmRtn) return;
        T000_READNEXT_CITRREL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRRELT);
            CIRRELT.KEY.RELT_NO = CIRRREL.RELT_NO;
            T000_READ_CITRELT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRRELT.RELT_TYPE);
            if (CIRRELT.RELT_TYPE.equalsIgnoreCase("01")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 11 - 1) + "1" + CIRBAS.IDE_STSW.substring(11 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("02")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 12 - 1) + "1" + CIRBAS.IDE_STSW.substring(12 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("03")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 13 - 1) + "1" + CIRBAS.IDE_STSW.substring(13 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("04")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 14 - 1) + "1" + CIRBAS.IDE_STSW.substring(14 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("05")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 15 - 1) + "1" + CIRBAS.IDE_STSW.substring(15 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("06")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 16 - 1) + "1" + CIRBAS.IDE_STSW.substring(16 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("07")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 17 - 1) + "1" + CIRBAS.IDE_STSW.substring(17 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("08")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 18 - 1) + "1" + CIRBAS.IDE_STSW.substring(18 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("09")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 19 - 1) + "1" + CIRBAS.IDE_STSW.substring(19 + 1 - 1);
            } else {
            }
            T000_READNEXT_CITRREL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITRREL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
        CIRBAS.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.ORGIN_TP = CICSOCC.DATA.ORGIN_TP;
        CIRBAS.ORIGIN = CICSOCC.DATA.ORGIN1;
        CIRBAS.ORIGIN2 = CICSOCC.DATA.ORGIN2;
        CIRBAS.CI_NM = CICSOCC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICSOCC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSOCC.DATA.ID_NO;
        CIRBAS.TAX_BANK = CICSOCC.DATA.TAX_BANK;
        BAS_TAX_BANK_LEN = CIRBAS.TAX_BANK.length();
        CIRBAS.TAX_AC_NO = CICSOCC.DATA.TAX_ACNO;
        CIRBAS.TAX_TYPE = CICSOCC.DATA.TAX_TYPE;
        CIRBAS.TAX_DIST_NO = CICSOCC.DATA.TAX_DSNO;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CIRBAS.OWNER_BK = BPCPQORG.BRANCH_BR;
        CIRBAS.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.OIC_NO = CICSOCC.DATA.OIC_NO;
        CIRBAS.OPEN_CHNL = SCCGWA.COMM_AREA.CHNL;
        CIRBAS.NRA_TAX_TYP = CICSOCC.DATA.NRA_TAX;
        CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.SEARCH_DT = CICSOCC.DATA.SCH_DT;
        CIRBAS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (WS_BAS_FOUND_FLG == 'N') {
            T000_WRITE_CITBAS();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_CITBAS();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        IBS.init(SCCGWA, CIRCDM);
        CIRCDM.KEY.CI_NO = WS_CI_NO;
        CIRCDM.RESIDENT = CICSOCC.DATA.RESIDENT;
        CIRCDM.CI_SUB_TYP = CICSOCC.DATA.SUB_TYP;
        CIRCDM.ORG_TYPE = CICSOCC.DATA.ORG_TYPE;
        CIRCDM.ECO = CICSOCC.DATA.ECO;
        CIRCDM.HECO = CICSOCC.DATA.HECO;
        CIRCDM.NED_TYP = CICSOCC.DATA.NED_TYP;
        CIRCDM.REG_DT = CICSOCC.DATA.REG_DT;
        CIRCDM.REG_CCY = CICSOCC.DATA.REG_CCY;
        CIRCDM.REG_AMT = CICSOCC.DATA.REG_AMT;
