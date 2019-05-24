package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCRPARM;

public class BPZMMTL {
    boolean pgmRtn = false;
    int WS_I = 0;
    int WS_TBLSIZE = 0;
    char WS_EOF_FLG = ' ';
    String WS_PARM_KEY = " ";
    BPZMMTL_WS_PARM WS_PARM = new BPZMMTL_WS_PARM();
    String WS_PARM_TABLE = " ";
    BPZMMTL_WS_KEY WS_KEY = new BPZMMTL_WS_KEY();
    short WS_COUNT = 0;
    SCRXMLC SCRXMLC = new SCRXMLC();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPDME BPRPDME = new BPRPDME();
    BPRPDCD BPRPDCD = new BPRPDCD();
    BPRORGS BPRORGS = new BPRORGS();
    BPRPARM BPRPARM = new BPRPARM();
    SCRPARM SCRPARM2 = new SCRPARM();
    AIRENTY AIRENTY = new AIRENTY();
    AIRGLM AIRGLM = new AIRGLM();
    BPRBPRD BPRBPRD = new BPRBPRD();
    BPRCALN BPRCALN = new BPRCALN();
    BPRBCCY BPRBCCY = new BPRBCCY();
    BPRORGM BPRORGM = new BPRORGM();
    CIRDTL CIRDTL = new CIRDTL();
    CIRDINF CIRDINF = new CIRDINF();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCMMTL BPCMMTL;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCMMTL BPCMMTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCMMTL = BPCMMTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZMMTL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCMMTL.RC.RC_APP = "BP";
        BPCMMTL.RC.RC_RTNCODE = 0;
        if (BPCMMTL.MMT_IDX < 1 
            || BPCMMTL.MMT_IDX > 12) {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID IDX(" + BPCMMTL.MMT_IDX + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        BPRPARM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_HASH_INIT();
        if (pgmRtn) return;
        if (BPCMMTL.MMT_IDX == 2) {
            B200_LOAD_SCTXMLC();
            if (pgmRtn) return;
        }
        if (BPCMMTL.MMT_IDX == 7) {
            B200_LOAD_BPTPARP();
            if (pgmRtn) return;
            B200_LOAD_BPTPDME();
            if (pgmRtn) return;
            B200_LOAD_BPTPDCD();
            if (pgmRtn) return;
            B200_LOAD_BPTORGM();
            if (pgmRtn) return;
        }
        if (BPCMMTL.MMT_IDX == 10) {
            B200_LOAD_BPTBPRD();
            if (pgmRtn) return;
            B200_LOAD_BPTCALN();
            if (pgmRtn) return;
            B200_LOAD_BPTBCCY();
            if (pgmRtn) return;
        }
        if (BPCMMTL.MMT_IDX == 12) {
            B200_LOAD_CIINFO();
            if (pgmRtn) return;
        }
        if (BPCMMTL.MMT_IDX == 11) {
            B200_LOAD_BPTORGS();
            if (pgmRtn) return;
        } else {
            if ((BPCMMTL.MMT_IDX != 2 
                && BPCMMTL.MMT_IDX != 7 
                && BPCMMTL.MMT_IDX != 10 
                && BPCMMTL.MMT_IDX != 12 
                && BPCMMTL.MMT_IDX != 11)) {
                B300_LOAD_PARM();
                if (pgmRtn) return;
            }
        }
        B400_GET_USED();
        if (pgmRtn) return;
    }
    public void B100_HASH_INIT() throws IOException,SQLException,Exception {
        LK_MMT = IBS.CLS2CPY(SCCGWA, BPCMMTL.MMT_PTR);
        if (BPCMMTL.MMT_IDX == 11) {
            WS_TBLSIZE = BPCMMTL.MAX_MMT_LEN / 100;
        } else {
            WS_TBLSIZE = BPCMMTL.MAX_MMT_LEN / 250;
        }
    }
    public void B200_LOAD_SCTXMLC() throws IOException,SQLException,Exception {
        WS_EOF_FLG = ' ';
        T000_STARTBR_SCTXMLC();
        if (pgmRtn) return;
        T000_READNEXT_SCTXMLC();
        if (pgmRtn) return;
        LK_MMT = IBS.CLS2CPY(SCCGWA, BPCMMTL.MMT_PTR);
        while (WS_EOF_FLG != 'Y') {
            IBS.HASHADD(SCCGWA, SCRXMLC.KEY.TYPE, SCRXMLC.KEY, SCRXMLC);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                IBS.init(SCCGWA, SCCEXCP);
