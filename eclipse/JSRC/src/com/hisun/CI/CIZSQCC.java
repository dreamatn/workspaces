package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSQCC {
    boolean pgmRtn = false;
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCDM CIRCDM = new CIRCDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRRISK CIRRISK = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    CICOSQCC CICOSQCC = new CICOSQCC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSQCC CICSQCC;
    public void MP(SCCGWA SCCGWA, CICSQCC CICSQCC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSQCC = CICSQCC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSQCC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQCC.RC);
        IBS.init(SCCGWA, CICOSQCC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_COM_BAS_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_COM_BAS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSQCC.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || CIRBAS.CI_ATTR != '1') {
            CEP.TRC(SCCGWA, "BAS INF NOT FND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP != '2') {
            CEP.TRC(SCCGWA, "CI TYP ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYP_INPUT_ERR);
        }
        R000_TRANS_BAS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRCDM);
        CIRCDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITCDM();
        if (pgmRtn) return;
        R000_TRANS_CDM_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        CEP.TRC(SCCGWA, CIRID.KEY.ID_TYPE);
        CEP.TRC(SCCGWA, CIRBAS.ID_TYPE);
        T000_READ_CITID();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRID.REMARK);
        CICOSQCC.DATA.ID_RMK = CIRID.REMARK;
        CEP.TRC(SCCGWA, CICOSQCC.DATA.ID_RMK);
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICOSQCC.DATA.CI_ENM = CIRNAM.CI_NM;
        }
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "09";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICOSQCC.DATA.CI_ONM = CIRNAM.CI_NM;
        }
        IBS.init(SCCGWA, CIRRISK);
        CIRRISK.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITRISK();
        if (pgmRtn) return;
        R000_TRANS_RISK_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRCRS);
        CIRCRS.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRCRS.KEY.INFO_TYP = '1';
        T000_STARTBR_CITCRS();
        if (pgmRtn) return;
        T000_READNEXT_CITCRS();
        if (pgmRtn) return;
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I <= 25; WS_I += 1) {
            R000_TRANS_CRS_DATA_TO_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_CITCRS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCRS();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_BAS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQCC.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOSQCC.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOSQCC.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOSQCC.DATA.STSW = CIRBAS.STSW;
        CICOSQCC.DATA.IDE_STSW = CIRBAS.IDE_STSW;
        CICOSQCC.DATA.CI_NM = CIRBAS.CI_NM;
        CICOSQCC.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOSQCC.DATA.ID_NO = CIRBAS.ID_NO;
        CICOSQCC.DATA.OIC_NO = CIRBAS.OIC_NO;
        CICOSQCC.DATA.NRA_TAX = CIRBAS.NRA_TAX_TYP;
        CICOSQCC.DATA.ORGIN_TP = CIRBAS.ORGIN_TP;
        CICOSQCC.DATA.ORGIN1 = CIRBAS.ORIGIN;
        CICOSQCC.DATA.ORGIN2 = CIRBAS.ORIGIN2;
        CICOSQCC.DATA.RESP_CD = CIRBAS.RESP_CD;
        CICOSQCC.DATA.SUB_DP = CIRBAS.SUB_DP;
        CICOSQCC.DATA.TAX_BANK = CIRBAS.TAX_BANK;
        CICOSQCC.DATA.TAX_ACNO = CIRBAS.TAX_AC_NO;
        CICOSQCC.DATA.TAX_TYPE = CIRBAS.TAX_TYPE;
        CICOSQCC.DATA.TAX_DSNO = CIRBAS.TAX_DIST_NO;
        CICOSQCC.DATA.SCH_DT = CIRBAS.SEARCH_DT;
    }
    public void R000_TRANS_CDM_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQCC.DATA.SUB_TYP = CIRCDM.CI_SUB_TYP;
        CICOSQCC.DATA.RESIDENT = CIRCDM.RESIDENT;
        CICOSQCC.DATA.ORG_TYPE = CIRCDM.ORG_TYPE;
        CICOSQCC.DATA.ECO = CIRCDM.ECO;
        CICOSQCC.DATA.HECO = CIRCDM.HECO;
        CICOSQCC.DATA.INDUS1 = CIRCDM.INDUS1;
        CICOSQCC.DATA.ENC_CD = CIRCDM.ENC_CD;
        CICOSQCC.DATA.SID_FLG = CIRCDM.SID_FLG;
        CICOSQCC.DATA.SIZE = CIRCDM.COMP_SIZE;
        CICOSQCC.DATA.BUSN_SCP = CIRCDM.BUSN_SCP;
