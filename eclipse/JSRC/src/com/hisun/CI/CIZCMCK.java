package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZCMCK {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    DBParm CITID_RD;
    DBParm CITCNT_RD;
    DBParm CITADR_RD;
    DBParm CITRELN_RD;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRID CIRID = new CIRID();
    CIRCNT CIRCNT = new CIRCNT();
    CIRADR CIRADR = new CIRADR();
    CIRRELN CIRRELN = new CIRRELN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCMCK CICCMCK;
    public void MP(SCCGWA SCCGWA, CICCMCK CICCMCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCMCK = CICCMCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZCMCK return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCMCK.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_CHK_CUST_INFO();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCMCK.DATA.CI_TYP);
        if (CICCMCK.DATA.CI_TYP == ' ') {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            T000_READ_CITBAS();
            CICCMCK.DATA.CI_TYP = CIRBAS.CI_TYP;
        }
    }
    public void B020_CHK_CUST_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCMCK.DATA.CI_TYP);
        if (CICCMCK.DATA.CI_TYP == '1') {
            B020_01_CHK_PER_CUST_INFO();
        } else {
        }
        CEP.TRC(SCCGWA, CICCMCK.DATA.COM_CHK);
        if (CICCMCK.DATA.TABLE_FLG == 'W') {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            T000_READ_CITBAS_UPD();
            if (CICCMCK.DATA.COM_CHK == 'N') {
                if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                JIBS_tmp_int = CIRBAS.STSW.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
            } else {
                if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                JIBS_tmp_int = CIRBAS.STSW.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "0" + CIRBAS.STSW.substring(24 + 1 - 1);
            }
            T000_REWRITE_CITBAS();
        }
    }
    public void B020_01_CHK_PER_CUST_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCMCK.DATA.PDM_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ID_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.CNT_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ADR_CHK);
        if (CICCMCK.DATA.PDM_CHK != 'Y') {
            IBS.init(SCCGWA, CIRPDM);
            CIRPDM.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            T000_READ_CITPDM();
            if (CIRPDM.SEX == ' ' 
                || CIRPDM.REG_CNTY.trim().length() == 0 
                || CIRPDM.OCCUP.trim().length() == 0) {
                CICCMCK.DATA.PDM_CHK = 'N';
            } else {
                CICCMCK.DATA.PDM_CHK = 'Y';
            }
        }
        if (CICCMCK.DATA.ID_CHK != 'Y') {
            IBS.init(SCCGWA, CIRID);
            CIRID.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            T000_READ_CITID_OPEN();
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, CIRID.EXP_DT);
            if (CIRID.EXP_DT == 0) {
                CICCMCK.DATA.ID_CHK = 'N';
            } else {
                CICCMCK.DATA.ID_CHK = 'Y';
            }
        }
        if (CICCMCK.DATA.CNT_CHK != 'Y') {
            IBS.init(SCCGWA, CIRCNT);
            CIRCNT.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            T000_READ_CITCNT_FIRST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CICCMCK.DATA.CNT_CHK = 'Y';
            } else {
                CICCMCK.DATA.CNT_CHK = 'N';
            }
        }
        if (CICCMCK.DATA.ADR_CHK != 'Y') {
            IBS.init(SCCGWA, CIRADR);
            CIRADR.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            T000_READ_CITADR_FIRST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CICCMCK.DATA.ADR_CHK = 'Y';
            } else {
                CICCMCK.DATA.ADR_CHK = 'N';
            }
        }
        CEP.TRC(SCCGWA, CICCMCK.DATA.PDM_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ID_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.CNT_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ADR_CHK);
        if (CICCMCK.DATA.PDM_CHK == 'Y' 
            && CICCMCK.DATA.ID_CHK == 'Y' 
            && CICCMCK.DATA.CNT_CHK == 'Y' 
            && CICCMCK.DATA.ADR_CHK == 'Y') {
            CICCMCK.DATA.COM_CHK = 'Y';
        } else {
            CICCMCK.DATA.COM_CHK = 'N';
        }
    }
    public void B020_02_CHK_COM_CUST_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCMCK.DATA.CDM_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ID_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ADR_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.REL_CHK);
        if (CICCMCK.DATA.CDM_CHK != 'Y') {
            IBS.init(SCCGWA, CIRCDM);
            CIRCDM.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            T000_READ_CITCDM();
            if (CIRCDM.BUSN_SCP.trim().length() == 0) {
                CICCMCK.DATA.PDM_CHK = 'N';
            } else {
                CICCMCK.DATA.PDM_CHK = 'Y';
            }
        }
        if (CICCMCK.DATA.ID_CHK != 'Y') {
            IBS.init(SCCGWA, CIRID);
            CIRID.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            T000_READ_CITID_OPEN();
            if (CIRID.EXP_DT == 0) {
                CICCMCK.DATA.ID_CHK = 'N';
            } else {
                CICCMCK.DATA.ID_CHK = 'Y';
            }
        }
        if (CICCMCK.DATA.ADR_CHK != 'Y') {
            IBS.init(SCCGWA, CIRADR);
            CIRADR.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            CIRADR.KEY.ADR_TYPE = "210";
            T000_READ_CITADR();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CICCMCK.DATA.ADR_CHK = 'N';
            } else {
                CICCMCK.DATA.ADR_CHK = 'Y';
            }
        }
        if (CICCMCK.DATA.REL_CHK != 'Y') {
            CICCMCK.DATA.REL_CHK = 'Y';
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            CIRRELN.KEY.CIREL_CD = "20101";
            T000_READ_CITRELN_FIRST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CICCMCK.DATA.REL_CHK = 'N';
            }
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            CIRRELN.KEY.CIREL_CD = "20201";
            T000_READ_CITRELN_FIRST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CICCMCK.DATA.REL_CHK = 'N';
            }
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            CIRRELN.KEY.CIREL_CD = "20204";
            T000_READ_CITRELN_FIRST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CICCMCK.DATA.REL_CHK = 'N';
            }
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            CIRRELN.KEY.CIREL_CD = "29999";
            T000_READ_CITRELN_FIRST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CICCMCK.DATA.REL_CHK = 'N';
            }
        }
        CEP.TRC(SCCGWA, CICCMCK.DATA.CDM_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ID_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ADR_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.REL_CHK);
        if (CICCMCK.DATA.CDM_CHK == 'Y' 
            && CICCMCK.DATA.ID_CHK == 'Y' 
            && CICCMCK.DATA.ADR_CHK == 'Y' 
            && CICCMCK.DATA.REL_CHK == 'Y') {
            CICCMCK.DATA.COM_CHK = 'Y';
        } else {
            CICCMCK.DATA.COM_CHK = 'N';
        }
    }
    public void B020_03_CHK_FIN_CUST_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCMCK.DATA.ID_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ADR_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.REL_CHK);
        if (CICCMCK.DATA.ID_CHK != 'Y') {
            IBS.init(SCCGWA, CIRID);
            CIRID.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            T000_READ_CITID_OPEN();
            if (CIRID.EXP_DT == 0) {
                CICCMCK.DATA.ID_CHK = 'N';
            } else {
                CICCMCK.DATA.ID_CHK = 'Y';
            }
        }
        if (CICCMCK.DATA.ADR_CHK != 'Y') {
            IBS.init(SCCGWA, CIRADR);
            CIRADR.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            CIRADR.KEY.ADR_TYPE = "210";
            T000_READ_CITADR();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CICCMCK.DATA.ADR_CHK = 'N';
            } else {
                CICCMCK.DATA.ADR_CHK = 'Y';
            }
        }
        if (CICCMCK.DATA.REL_CHK != 'Y') {
            CICCMCK.DATA.REL_CHK = 'Y';
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            CIRRELN.KEY.CIREL_CD = "20201";
            T000_READ_CITRELN_FIRST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CICCMCK.DATA.REL_CHK = 'N';
            }
            IBS.init(SCCGWA, CIRRELN);
            CIRRELN.KEY.CI_NO = CICCMCK.DATA.CI_NO;
            CIRRELN.KEY.CIREL_CD = "29999";
            T000_READ_CITRELN_FIRST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CICCMCK.DATA.REL_CHK = 'N';
            }
        }
        CEP.TRC(SCCGWA, CICCMCK.DATA.ID_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.ADR_CHK);
        CEP.TRC(SCCGWA, CICCMCK.DATA.REL_CHK);
        if (CICCMCK.DATA.ID_CHK == 'Y' 
            && CICCMCK.DATA.ADR_CHK == 'Y' 
            && CICCMCK.DATA.REL_CHK == 'Y') {
            CICCMCK.DATA.COM_CHK = 'Y';
        } else {
            CICCMCK.DATA.COM_CHK = 'N';
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITBAS_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.READ(SCCGWA, CIRPDM, CITPDM_RD);
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
    public void T000_READ_CITID_OPEN() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.eqWhere = "CI_NO";
        CITID_RD.where = "OPEN = 'Y'";
        IBS.READ(SCCGWA, CIRID, this, CITID_RD);
    }
    public void T000_READ_CITCNT_FIRST() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.eqWhere = "CI_NO";
        CITCNT_RD.fst = true;
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void T000_READ_CITADR_FIRST() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        CITADR_RD.eqWhere = "CI_NO";
        CITADR_RD.fst = true;
        IBS.READ(SCCGWA, CIRADR, CITADR_RD);
    }
    public void T000_READ_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        IBS.READ(SCCGWA, CIRADR, CITADR_RD);
    }
    public void T000_READ_CITRELN_FIRST() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        CITRELN_RD.eqWhere = "CI_NO,CIREL_CD";
        CITRELN_RD.fst = true;
        IBS.READ(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
