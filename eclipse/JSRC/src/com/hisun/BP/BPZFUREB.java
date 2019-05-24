package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZFUREB {
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String K_HIS_REMARKS = "FEE BAS INFOMATION MAINTAIN";
    String K_CPY_BPRFECRE = "BPRFECRE";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPVFOREB BPVFOREB = new BPVFOREB();
    BPRFECRE BPRFECRE = new BPRFECRE();
    BPRFECRE BPROECRE = new BPRFECRE();
    BPCTFECR BPCTFECR = new BPCTFECR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCOFECR BPCOUECR;
    BPCOFECR BPCOFECR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFECR BPCOUECR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUECR = BPCOUECR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFUREB return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCTFECR);
        IBS.init(SCCGWA, BPROECRE);
        IBS.init(SCCGWA, BPRFECRE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOUECR.FUNC == 'A') {
            CEP.TRC(SCCGWA, "BBB");
            CEP.TRC(SCCGWA, BPCOUECR.VAL.PER_CNT);
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUECR.FUNC == 'U') {
            CEP.TRC(SCCGWA, "CCC");
            B030_01_COMPARE_DATA();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUECR.FUNC == 'D') {
            CEP.TRC(SCCGWA, "DDD");
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FFF");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFECRE);
        R000_TRANS_DATA_INPUT();
        if (pgmRtn) return;
        BPCTFECR.INFO.FUNC = 'C';
        S000_CALL_BPZTFECR();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START ADD HISTORY WHEN ADD");
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCOUECR.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else if (BPCOUECR.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROECRE;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRFECRE;
        } else if (BPCOUECR.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRFECRE;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFECRE);
        BPCTFECR.INFO.FUNC = 'R';
