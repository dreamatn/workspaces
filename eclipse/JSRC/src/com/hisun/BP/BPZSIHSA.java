package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIHSA {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_U_INQ_FHISA = "BP-U-INQ-FHISA";
    String K_OUTPUT_FMT = "BP044";
    short WS_RC_CNT = 0;
    int WS_BROW_AC_DT = 0;
    int WS_TEMP_AC_DT = 0;
    BPZSIHSA_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSIHSA_WS_OUTPUT_DATA();
    char WS_STOP_FLG = ' ';
    char WS_FHISA_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    BPCUFHSA BPCUFHSA = new BPCUFHSA();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCSIHSA BPCSIHSA;
    public void MP(SCCGWA SCCGWA, BPCSIHSA BPCSIHSA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIHSA = BPCSIHSA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIHSA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUFHSA);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSIHSA.RC);
        WS_STOP_FLG = 'N';
        WS_FHISA_FND_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_BROWSE_HISA();
        if (pgmRtn) return;
        B050_TRANS_DATA();
        if (pgmRtn) return;
        B070_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        R200_INQ_STD_AC();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_HISA() throws IOException,SQLException,Exception {
        BPCUFHSA.DATA.FUNC_FLG = 'Y';
        BPCUFHSA.DATA.END_AC_DT = BPCSIHSA.DATA.END_AC_DT;
        BPCUFHSA.DATA.STR_AC_DT = BPCSIHSA.DATA.STR_AC_DT;
        BPCUFHSA.DATA.AC = BPCSIHSA.DATA.AC;
        BPCUFHSA.DATA.CCY = BPCSIHSA.DATA.CCY;
        S000_CALL_BPZUFHSA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUFHSA.DATA.OUTPUT_DATA.REC_NUM);
    }
    public void B050_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_REC_NUM = BPCUFHSA.DATA.OUTPUT_DATA.REC_NUM;
        WS_RC_CNT = 1;
        while (WS_RC_CNT <= WS_OUTPUT_DATA.WS_REC_NUM) {
            CEP.TRC(SCCGWA, WS_RC_CNT);
            CEP.TRC(SCCGWA, BPCUFHSA.DATA.OUTPUT_DATA.TS_DATA[WS_RC_CNT-1].TS_AC_DT);
            WS_OUTPUT_DATA.WS_TS_DATA[WS_RC_CNT-1].WS_TS_AC_DT = BPCUFHSA.DATA.OUTPUT_DATA.TS_DATA[WS_RC_CNT-1].TS_AC_DT;
            WS_OUTPUT_DATA.WS_TS_DATA[WS_RC_CNT-1].WS_TS_AC_BAL = BPCUFHSA.DATA.OUTPUT_DATA.TS_DATA[WS_RC_CNT-1].TS_AC_BAL;
            WS_OUTPUT_DATA.WS_TS_DATA[WS_RC_CNT-1].WS_FILLER = 0X01;
            WS_RC_CNT += 1;
        }
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_REC_NUM);
    }
    public void B070_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 5026;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R200_INQ_STD_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIHSA.DATA.AC);
        IBS.init(SCCGWA, DCCUSPAC);
        DCCUSPAC.FUNC.AC = BPCSIHSA.DATA.AC;
        S000_CALL_DCZUSPAC();
        if (pgmRtn) return;
        if (DCCUSPAC.OUTPUT.STD_AC.trim().length() > 0) {
            BPCSIHSA.DATA.AC = DCCUSPAC.OUTPUT.STD_AC;
        }
        CEP.TRC(SCCGWA, DCCUSPAC.OUTPUT.STD_AC);
    }
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-STD-AC", DCCUSPAC);
    }
    public void S000_CALL_BPZUFHSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_INQ_FHISA, BPCUFHSA);
        CEP.TRC(SCCGWA, BPCUFHSA.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHSA.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHSA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSIHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
