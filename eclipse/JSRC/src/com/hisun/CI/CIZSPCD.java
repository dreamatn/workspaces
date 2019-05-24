package com.hisun.CI;

import com.hisun.DD.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSPCD {
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITID_RD;
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRID CIRID = new CIRID();
    CICCHCI CICCHCI = new CICCHCI();
    DDCSUAC DDCSUAC = new DDCSUAC();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSPCD CICSPCD;
    public void MP(SCCGWA SCCGWA, CICSPCD CICSPCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSPCD = CICSPCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSPCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSPCD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_VFCTION_PUBLIC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSPCD.DATA.CI_NO_O;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICSPCD.DATA.AGR_NO;
            CIRACR.CI_NO = CICSPCD.DATA.CI_NO_O;
            T000_READ_CITACR_EXIST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "SPCD-AGR-NO IS ERROR");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
            }
        } else {
            CEP.TRC(SCCGWA, "SPCD-CI-NO-O IS ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        }
    }
    public void B020_VFCTION_PUBLIC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSPCD.DATA.CI_NO_O);
        CEP.TRC(SCCGWA, CICSPCD.DATA.CI_NO_N);
        if (CICSPCD.DATA.CI_NO_O.equalsIgnoreCase(CICSPCD.DATA.CI_NO_N)) {
            B021_OLD_NEW_EQ_PROC();
            if (pgmRtn) return;
        } else {
            B022_OLD_NEW_INEQ_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_OLD_NEW_EQ_PROC() throws IOException,SQLException,Exception {
        CIRBAS.ID_TYPE = CICSPCD.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSPCD.DATA.ID_NO;
        T000_REWRITE_TABLE_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CICSPCD.DATA.CI_NO_O;
        T000_READ_TABLE_CITID();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CIRID.KEY.ID_TYPE = CICSPCD.DATA.ID_TYPE;
            CIRID.ID_NO = CICSPCD.DATA.ID_NO;
            CEP.TRC(SCCGWA, CIRID.KEY.ID_TYPE);
            CEP.TRC(SCCGWA, CIRID.ID_NO);
            T000_REWRITE_TABLE_CITID();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "SPCD-CI-NO-O NOT IN CITID");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        }
        S000_AC_TYP_CHANGE_PROC();
        if (pgmRtn) return;
    }
    public void B022_OLD_NEW_INEQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSPCD.DATA.CI_NO_N;
        CIRBAS.ID_TYPE = CICSPCD.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSPCD.DATA.ID_NO;
        T000_READ_TABLE_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI-NO-N,ID-TYPE,ID-NO NOT IN CITBAS");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        }
        IBS.init(SCCGWA, CICCHCI);
        CICCHCI.INPUT.OLD_CINO = CICSPCD.DATA.CI_NO_O;
        CICCHCI.INPUT.NEW_CINO = CICSPCD.DATA.CI_NO_N;
        CICCHCI.INPUT.AC_NO = CICSPCD.DATA.AGR_NO;
        R000_CALL_CIZCHCI_PROC();
        if (pgmRtn) return;
        S000_AC_TYP_CHANGE_PROC();
        if (pgmRtn) return;
    }
    public void S000_AC_TYP_CHANGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSUAC);
        DDCSUAC.BASIC_INF.AC_NO = CICSPCD.DATA.AGR_NO;
        DDCSUAC.BASIC_INF.AC_TYPE = CICSPCD.DATA.AC_TYP;
        DDCSUAC.COMP_INF.FLG = 'Y';
        S000_CALL_DDZSUAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSUAC.BASIC_INF.AC_TYPE);
    }
    public void S000_CALL_DDZSUAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-UPDATE-ACTX", DDCSUAC, true);
    }
    public void R000_CALL_CIZCHCI_PROC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHCI", CICCHCI, true);
        if (CICCHCI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CHCI-RC-CODE = ");
            CEP.TRC(SCCGWA, CICCHCI.RC.RC_CODE);
            CEP.TRC(SCCGWA, "CALL CIZCHCI PROC IS WRONG");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCHCI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICSPCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "CI_NO";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.eqWhere = "AGR_NO,CI_NO";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_REWRITE_TABLE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_TABLE_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.eqWhere = "CI_NO";
        CITID_RD.upd = true;
        IBS.READ(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_REWRITE_TABLE_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.REWRITE(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_READ_TABLE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "CI_NO,ID_TYPE,ID_NO";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
