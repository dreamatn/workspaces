package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZQPDTL {
    boolean pgmRtn = false;
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "CI";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_N = 0;
    short WS_IDX = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    int WS_LC_SEQ = 0;
    int WS_SUB_SEQ = 0;
    int WS_SEQ = 0;
    String WS_LC_OBJ_TYP = " ";
    int WS_LAST_LC_NO = 0;
    int WS_LAST_SEQ = 0;
    int WS_LAST_SUB_SEQ = 0;
    String WS_LAST_CON_TYP = " ";
    char WS_MSREL_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_GOPP_FLG = ' ';
    char WS_DTL_FLG = ' ';
    char WS_DINF_FLG = ' ';
    char WS_EQUAL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    CICOPDTL CICOPDTL = new CICOPDTL();
    CIRTYP CIRTYP = new CIRTYP();
    CIRDTL CIRDTL = new CIRDTL();
    CIRGOPP CIRGOPP = new CIRGOPP();
    CIRDINF CIRDINF = new CIRDINF();
    CIRSTYP CIRSTYP = new CIRSTYP();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICQPDTL CICQPDTL;
    public void MP(SCCGWA SCCGWA, CICQPDTL CICQPDTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQPDTL = CICQPDTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQPDTL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, CIRTYP);
        IBS.init(SCCGWA, CIRGOPP);
        IBS.init(SCCGWA, CIRDTL);
        IBS.init(SCCGWA, CIRDINF);
        IBS.init(SCCGWA, CIRSTYP);
        IBS.init(SCCGWA, CICOPDTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, CICQPDTL.FUNC);
