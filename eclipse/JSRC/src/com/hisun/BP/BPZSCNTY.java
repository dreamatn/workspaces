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

public class BPZSCNTY {
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_CPN_PARM_MAINTAIN = "BP-PARM-MAINTAIN";
    String K_CPN_R_FEE_BPZRMBPM = "BP-R-MBRW-PARM";
    String K_HIS_REMARKS = "COUNTRY CODE MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHPCD";
    String K_PARM_TYPE_TYPE = "CNTY ";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP741";
    String WS_ERR_MSG = " ";
    char WS_OUTPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRCNTY BPRRCNTY = new BPRCNTY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPVCNTY BPVNCNTY = new BPVCNTY();
    BPVCNTY BPVOCNTY = new BPVCNTY();
    BPCOCNTY BPCOCNTY = new BPCOCNTY();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSCNTY BPCSCNTY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCNTY BPCSCNTY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCNTY = BPCSCNTY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCNTY return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRRCNTY);
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCRMBPM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCNTY);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSCNTY.FUNC == 'Q') {
            B010_QUERY_CNTY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCNTY.FUNC == 'A') {
            B020_ADD_CNTY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCNTY.FUNC == 'U') {
            B030_UPDATE_CNTY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCNTY.FUNC == 'D') {
            B040_DELETE_CNTY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCNTY.FUNC == 'B') {
            B050_BROWSE_CNTY_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSCNTY.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCNTY.FUNC);
        if (BPCSCNTY.FUNC == 'Q' 
            && BPCSCNTY.FUNC == 'A' 
            && BPCSCNTY.FUNC == 'U' 
            && BPCSCNTY.FUNC == 'B' 
            && BPCSCNTY.FUNC == 'D') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSCNTY.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, BPCSCNTY.KEY.CODE);
        if (BPCSCNTY.FUNC == 'Q' 
            || BPCSCNTY.FUNC == 'A' 
            || BPCSCNTY.FUNC == 'U' 
            || BPCSCNTY.FUNC == 'D') {
            if (BPCSCNTY.KEY.CODE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_CD_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_QUERY_CNTY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRCNTY);
        IBS.init(SCCGWA, BPCPRMM);
        BPRRCNTY.KEY.TYP = "CNTY";
        BPRRCNTY.KEY.CD = BPCSCNTY.KEY.CODE;
        BPCPRMM.EFF_DT = BPCSCNTY.EFF_DATE;
        CEP.TRC(SCCGWA, BPCSCNTY.EFF_DATE);
        CEP.TRC(SCCGWA, BPRRCNTY.KEY.CD);
        BPCPRMM.FUNC = '3';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_ADD_CNTY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRCNTY);
        IBS.init(SCCGWA, BPCPRMM);
        BPRRCNTY.KEY.TYP = "CNTY";
        BPRRCNTY.KEY.CD = BPCSCNTY.KEY.CODE;
        CEP.TRC(SCCGWA, BPCSCNTY.KEY.CODE);
        BPCPRMM.EFF_DT = BPCSCNTY.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSCNTY.EXP_DATE;
        BPRRCNTY.CDSC = BPCSCNTY.LOA_NAME;
        BPRRCNTY.DESC = BPCSCNTY.ENG_NAME;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNTY.DATA);
