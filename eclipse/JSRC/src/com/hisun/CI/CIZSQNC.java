package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSQNC {
    DBParm CITBAS_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    DBParm CITRISK_RD;
    brParm CITCRS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRRISK CIRRISK = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    CICOSQNC CICOSQNC = new CICOSQNC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSQNC CICSQNC;
    public void MP(SCCGWA SCCGWA, CICSQNC CICSQNC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSQNC = CICSQNC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSQNC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQNC.RC);
        IBS.init(SCCGWA, CICOSQNC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_NOS_BAS_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_NOS_BAS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSQNC.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BAS INF NOT FND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP == '1') {
            CEP.TRC(SCCGWA, "CI TYP ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYP_INPUT_ERR);
        }
        R000_TRANS_BAS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        if (CIRBAS.CI_TYP == '3') {
            IBS.init(SCCGWA, CIRFDM);
            CIRFDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
            T000_READ_CITFDM();
            if (pgmRtn) return;
            R000_TRANS_FDM_DATA_TO_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRCDM);
            CIRCDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
            T000_READ_CITCDM();
            if (pgmRtn) return;
            R000_TRANS_CDM_DATA_TO_OUTPUT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICOSQNC.DATA.ID_RMK = CIRID.REMARK;
        CICOSQNC.DATA.ID_RGN = CIRID.ID_RGN;
        CICOSQNC.DATA.ID_EXPDT = CIRID.EXP_DT;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICOSQNC.DATA.CI_ENM = CIRNAM.CI_NM;
        }
        CEP.TRC(SCCGWA, CICOSQNC.DATA.SUB_TYP);
        CEP.TRC(SCCGWA, CICOSQNC);
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_BAS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQNC.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOSQNC.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOSQNC.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOSQNC.DATA.STSW = CIRBAS.STSW;
        CICOSQNC.DATA.CI_NM = CIRBAS.CI_NM;
        CICOSQNC.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOSQNC.DATA.ID_NO = CIRBAS.ID_NO;
        CICOSQNC.DATA.OIC_NO = CIRBAS.OIC_NO;
        CICOSQNC.DATA.RESP_CD = CIRBAS.RESP_CD;
        CICOSQNC.DATA.SUB_DP = CIRBAS.SUB_DP;
    }
    public void R000_TRANS_FDM_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQNC.DATA.SUB_TYP = CIRFDM.CI_SUB_TYP;
        CICOSQNC.DATA.ORG_TYPE = CIRFDM.ORG_TYPE;
        CICOSQNC.DATA.FIN_TYPE = CIRFDM.FIN_TYPE;
        CICOSQNC.DATA.INDUS1 = CIRFDM.INDUS1;
        CICOSQNC.DATA.INDUS2 = CIRFDM.INDUS2;
        CICOSQNC.DATA.REG_AMT = CIRFDM.REG_AMT;
        CICOSQNC.DATA.REG_CCY = CIRFDM.REG_CCY;
        CICOSQNC.DATA.CAP_AMT = CIRFDM.CAP_AMT;
        CICOSQNC.DATA.CAP_CCY = CIRFDM.CAP_CCY;
        CICOSQNC.DATA.COR_NO = CIRFDM.COR_NO;
        CICOSQNC.DATA.M_COR_NO = CIRFDM.MAIN_COR_NO;
        CICOSQNC.DATA.STK_NO = CIRFDM.STK_NO;
        CICOSQNC.DATA.OWN_STR = CIRFDM.OWN_STR;
    }
    public void R000_TRANS_CDM_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICOSQNC.DATA.SUB_TYP = CIRCDM.CI_SUB_TYP;
        CICOSQNC.DATA.ORG_TYPE = CIRCDM.ORG_TYPE;
        CICOSQNC.DATA.INDUS1 = CIRCDM.INDUS1;
        CICOSQNC.DATA.INDUS2 = CIRCDM.INDUS2;
        CICOSQNC.DATA.REG_AMT = CIRCDM.REG_AMT;
        CICOSQNC.DATA.REG_CCY = CIRCDM.REG_CCY;
        CICOSQNC.DATA.CAP_AMT = CIRCDM.CAP_AMT;
        CICOSQNC.DATA.CAP_CCY = CIRCDM.CAP_CCY;
        CICOSQNC.DATA.STK_NO = CIRCDM.STK_NO;
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO06";
        SCCFMT.DATA_PTR = CICOSQNC;
        SCCFMT.DATA_LEN = 939;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
    }
    public void T000_READ_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_READ_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.READ(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_READ_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        IBS.READ(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_STARTBR_CITCRS() throws IOException,SQLException,Exception {
        CITCRS_BR.rp = new DBParm();
        CITCRS_BR.rp.TableName = "CITCRS";
        CITCRS_BR.rp.eqWhere = "CI_NO,INFO_TYP";
        IBS.STARTBR(SCCGWA, CIRCRS, CITCRS_BR);
    }
    public void T000_READNEXT_CITCRS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCRS, this, CITCRS_BR);
    }
    public void T000_ENDBR_CITCRS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCRS_BR);
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
