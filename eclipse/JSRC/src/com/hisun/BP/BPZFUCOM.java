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

public class BPZFUCOM {
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_Z_CERT_CHARGE = "BP-F-Z-CERT-CHARGE";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPVFCOM BPVFCOM = new BPVFCOM();
    BPVFOCOM BPVFOCOM = new BPVFOCOM();
    BPCFEEUC BPCFEEUC = new BPCFEEUC();
    BPCBVCHG BPCBVCHG = new BPCBVCHG();
    SCCGWA SCCGWA;
    BPCOFCOM BPCOUCOM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFCOM BPCOUCOM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUCOM = BPCOUCOM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFUCOM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPVFCOM);
        IBS.init(SCCGWA, BPCFPARM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOUCOM.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUCOM.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUCOM.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUCOM.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUCOM.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFEEUC);
        IBS.init(SCCGWA, BPCBVCHG);
        if (BPCOUCOM.KEY.FREE_FMT.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPCOUCOM.KEY.FREE_FMT);
            CEP.TRC(SCCGWA, BPCOUCOM.TXT);
            BPCFEEUC.KEY.FREE_FMT_KEY = BPCOUCOM.KEY.FREE_FMT;
            BPCFEEUC.TXT_DATA.TXT = BPCOUCOM.TXT;
            BPCBVCHG.INFO.FUNC = '3';
            BPCBVCHG.INFO.POINTER = BPCFEEUC;
            S000_CALL_BPZBVCHG();
            if (pgmRtn) return;
            if (BPCBVCHG.RETURN_INFO == 'N') {
                BPCOUCOM.FOUND_FLG = 'N';
            }
            BPCOUCOM.TXT = BPCFEEUC.TXT_DATA.TXT;
        }
        IBS.init(SCCGWA, BPVFCOM);
