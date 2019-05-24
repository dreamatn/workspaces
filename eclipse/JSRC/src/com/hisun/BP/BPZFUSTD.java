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

public class BPZFUSTD {
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_P_INQ_RGNC = "BP-P-INQ-RGNC       ";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY    ";
    String CPN_Z_CERT_CHARGE = "BP-F-Z-CERT-CHARGE";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    String WS_FREE_FMT_KEY = " ";
    String WS_EEUC_TYPE = " ";
    double WS_FEE = 0;
    char WS_REC_DATA_CHG_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPVFSTD BPVFSTD = new BPVFSTD();
    BPVFSTD BPVHSTD = new BPVFSTD();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCFEEUC BPCFEEUC = new BPCFEEUC();
    BPCBVCHG BPCBVCHG = new BPCBVCHG();
    SCCGWA SCCGWA;
    BPCOFSTD BPCOUSTD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFSTD BPCOUSTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUSTD = BPCOUSTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFUSTD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPVFSTD);
        IBS.init(SCCGWA, BPCFPARM);
        IBS.init(SCCGWA, BPCQCCY);
        IBS.init(SCCGWA, BPCFEEUC);
        IBS.init(SCCGWA, BPCBVCHG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOUSTD.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUSTD.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUSTD.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUSTD.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUSTD.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "END-B00-PROC");
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_KEY_VALIDITY();
        if (pgmRtn) return;
        R000_GET_BASE_INFOR();
        if (pgmRtn) return;
        BPCOUSTD.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        CEP.TRC(SCCGWA, BPCOUSTD.KEY.FEE_CODE);
        CEP.TRC(SCCGWA, BPCOUSTD.KEY);
        CEP.TRC(SCCGWA, BPCOUSTD.KEY.FREE_FMT);
        if (BPCOUSTD.KEY.FREE_FMT.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFEEUC);
            BPCFEEUC.KEY.FREE_FMT_KEY = BPCOUSTD.KEY.FREE_FMT;
            BPCFEEUC.TXT_DATA.TXT = BPCOUSTD.VAL.TXT;
            BPCBVCHG.INFO.POINTER = BPCFEEUC;
            BPCBVCHG.INFO.FUNC = '3';
            S000_CALL_BPZBVCHG();
            if (pgmRtn) return;
            if (BPCBVCHG.RETURN_INFO == 'F') {
                BPCOUSTD.VAL.TXT = BPCFEEUC.TXT_DATA.TXT;
                CEP.TRC(SCCGWA, BPCOUSTD.VAL.TXT);
            }
        }
        IBS.init(SCCGWA, BPVFSTD);
