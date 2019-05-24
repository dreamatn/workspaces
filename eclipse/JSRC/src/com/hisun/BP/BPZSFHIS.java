package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSFHIS {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    double WS_NEW_RATE = 0;
    double WS_OLD_RATE = 0;
    char WS_TABLE_REC = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSFHIS BPCRFHIS = new BPCSFHIS();
    BPRICSP BPRICSP = new BPRICSP();
    BPRICSP BPRICSPN = new BPRICSP();
    BPRICSP BPRICSPO = new BPRICSP();
    BPREXRD BPREXRD = new BPREXRD();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    BPRFEHIS BPRFEHIN = new BPRFEHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCSFHIS BPCSFHIS;
    BPRFEHIS BPRFRHIS;
    public void MP(SCCGWA SCCGWA, BPCSFHIS BPCSFHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFHIS = BPCSFHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFHIS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, "SET-ADD");
        BPRFRHIS = (BPRFEHIS) BPCSFHIS.INP_DATA.DATA_PTR;
        IBS.init(SCCGWA, BPRFEHIS);
        CEP.TRC(SCCGWA, "CHKLEN1");
        CEP.TRC(SCCGWA, BPCSFHIS.INP_DATA.DATA_LEN);
        CEP.TRC(SCCGWA, "5677");
        CEP.TRC(SCCGWA, "CHKLEN");
        IBS.CLONE(SCCGWA, BPRFRHIS, BPRFEHIS);
        BPCSFHIS.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCSFHIS.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        B002_NORMAL_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B002_CANCEL_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFEHIS, BPRFRHIS);
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CODE);
        CEP.TRC(SCCGWA, BPRFEHIS.TX_MMO);
        if (BPRFEHIS.FEE_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPRFBAS);
            BPRFBAS.KEY.FEE_CODE = BPRFEHIS.FEE_CODE;
            BPTFBAS_RD = new DBParm();
            BPTFBAS_RD.TableName = "BPTFBAS";
            IBS.READ(SCCGWA, BPRFBAS, BPTFBAS_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                BPCSFHIS.RET_INFO = 'N';
                Z_RET();
                if (pgmRtn) return;
            } else {
                BPCSFHIS.RET_INFO = 'O';
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPRFBAS.TX_MMO);
        } else {
            IBS.CPY2CLS(SCCGWA, "BP1111", BPCSFHIS.RC);
            CEP.TRC(SCCGWA, BPCSFHIS.RC);
            CEP.TRC(SCCGWA, "FEE-CD-ERR");
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B002_CANCEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFHIS);
        IBS.init(SCCGWA, BPRFEHIN);
        IBS.CLONE(SCCGWA, BPRFEHIS, BPRFEHIN);
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRFEHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        BPCRFHIS.FUNC = 'B';
        S000_CALL_BPZRFHIS();
        if (pgmRtn) return;
        BPCRFHIS.FUNC = 'N';
        S000_CALL_BPZRFHIS();
        if (pgmRtn) return;
        while (BPCRFHIS.RET_INFO != 'N') {
            BPCRFHIS.FUNC = 'R';
            S000_CALL_BPZRFHIS();
            if (pgmRtn) return;
            BPRFEHIS.TX_REV_DT = SCCGWA.COMM_AREA.TR_DATE;
            BPRFEHIS.TX_STS = 'C';
            BPCRFHIS.FUNC = 'U';
            S000_CALL_BPZRFHIS();
            if (pgmRtn) return;
            BPCRFHIS.FUNC = 'N';
            S000_CALL_BPZRFHIS();
            if (pgmRtn) return;
        }
        BPCRFHIS.FUNC = 'E';
        S000_CALL_BPZRFHIS();
        if (pgmRtn) return;
    }
    public void B002_NORMAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFHIS);
        BPCRFHIS.FUNC = 'A';
        BPRFEHIS.TX_MMO = BPRFBAS.TX_MMO;
        CEP.TRC(SCCGWA, BPRFEHIS.TX_MMO);
        S000_CALL_BPZRFHIS();
        if (pgmRtn) return;
        if (BPCRFHIS.RC.RC_CODE != 0) {
