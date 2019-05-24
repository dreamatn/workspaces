package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9390 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPOT9390_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT9390_WS_TEMP_VARIABLE();
    BPCOOTLS BPCOOTLS = new BPCOOTLS();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPB1310_AWA_1310 BPB1310_AWA_1310;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT9390 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CAL_LAST_CINO_PROC();
        if (pgmRtn) return;
        B300_OUTPUT_RESULT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CI_NO);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.NUM2);
        if (BPB1310_AWA_1310.CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CI_NO_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(BPB1310_AWA_1310.CI_NO)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CINO_MST_NUMERIC, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1310_AWA_1310.NUM2 > 100) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RSV_NUM_LAR_100, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_CAL_LAST_CINO_PROC() throws IOException,SQLException,Exception {
        if (BPB1310_AWA_1310.CI_NO.trim().length() == 0) WS_TEMP_VARIABLE.WS_CINO_NUM = 0;
        else WS_TEMP_VARIABLE.WS_CINO_NUM = Integer.parseInt(BPB1310_AWA_1310.CI_NO);
        WS_TEMP_VARIABLE.WS_NUM = BPB1310_AWA_1310.NUM2;
        WS_TEMP_VARIABLE.WS_CINO_NUM = WS_TEMP_VARIABLE.WS_CINO_NUM + WS_TEMP_VARIABLE.WS_NUM - 1;
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CINO_NUM);
    }
    public void B300_OUTPUT_RESULT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_TEMP_VARIABLE.WS_CINO = "" + WS_TEMP_VARIABLE.WS_CINO_NUM;
        JIBS_tmp_int = WS_TEMP_VARIABLE.WS_CINO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_CINO = "0" + WS_TEMP_VARIABLE.WS_CINO;
        SCCFMT.FMTID = "BP575";
        SCCFMT.DATA_LEN = 12;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CINO);
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
