package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPRDCHK;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class SMZRDCHK {
    boolean pgmRtn = false;
    String K_PGM_NAME = "SMZRDCHK";
    String K_TBL_DCHK = "BPTDCHK ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRDCHK BPRDCHK = new BPRDCHK();
    SCCGWA SCCGWA;
    SMCTCHKM SMCTCHKM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRDCHK BPRDCHKA;
    public void MP(SCCGWA SCCGWA, SMCTCHKM SMCTCHKM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCTCHKM = SMCTCHKM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZRDCHK return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRDCHKA = (BPRDCHK) SMCTCHKM.INFO.POINTER;
        IBS.init(SCCGWA, BPRDCHK);
        CEP.TRC(SCCGWA, SMCTCHKM.INFO.LEN);
        IBS.CLONE(SCCGWA, BPRDCHKA, BPRDCHK);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SMCTCHKM.RC.RC_MMO = "SM";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SMCTCHKM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SMCTCHKM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRDCHK, BPRDCHKA);
        } else if (SMCTCHKM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAA");
            CEP.TRC(SCCGWA, SMCTCHKM.INFO.LEN);
            IBS.CLONE(SCCGWA, BPRDCHK, BPRDCHKA);
            CEP.TRC(SCCGWA, "BBBBBBBBBBBBBBBB");
        } else if (SMCTCHKM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SMCTCHKM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, SMCTCHKM.RC);
            CEP.TRC(SCCGWA, SMCTCHKM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTDCHK();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDCHK_UPD();
        if (pgmRtn) return;
        if (WS_TBL_BANK_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_DCHK_NOTFND, SMCTCHKM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDCHK();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTDCHK();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTDCHK();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTDCHK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRDCHK.KEY);
        CEP.TRC(SCCGWA, BPRDCHK.KEY);
        BPTDCHK_RD = new DBParm();
        BPTDCHK_RD.TableName = "BPTDCHK";
        IBS.READ(SCCGWA, BPRDCHK, BPTDCHK_RD);
        CEP.TRC(SCCGWA, BPRDCHK.OPT);
        CEP.TRC(SCCGWA, BPRDCHK.RMK);
        CEP.TRC(SCCGWA, BPRDCHK.CRE_ID);
        CEP.TRC(SCCGWA, BPRDCHK.CRE_DATE);
        CEP.TRC(SCCGWA, BPRDCHK.UPD_DATE);
