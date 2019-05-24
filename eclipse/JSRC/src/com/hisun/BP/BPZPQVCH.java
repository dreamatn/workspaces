package com.hisun.BP;

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

public class BPZPQVCH {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    long WS_PRE_JRNNO = 0;
    BPZPQVCH_WS_OUT_DATA WS_OUT_DATA = new BPZPQVCH_WS_OUT_DATA();
    char WS_FILE_NAME = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRVCHT BPRVCHT = new BPRVCHT();
    BPCTVCHT BPCTVCHT = new BPCTVCHT();
    BPRVCHH BPRVCHH = new BPRVCHH();
    BPCTVCHH BPCTVCHH = new BPCTVCHH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQVCH BPCQVCH;
    public void MP(SCCGWA SCCGWA, BPCPQVCH BPCQVCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQVCH = BPCQVCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQVCH return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (BPCQVCH.FUNC == 'Q') {
            B020_INQUIRE_RECORDS();
            if (pgmRtn) return;
        } else if (BPCQVCH.FUNC == 'S') {
            B030_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCQVCH.FUNC == 'N') {
            B040_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCQVCH.FUNC == 'E') {
            B050_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
