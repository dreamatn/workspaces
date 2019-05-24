package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1840 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMOT1840_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1840_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    SMOT1840_WS_TS_CNTL WS_TS_CNTL = new SMOT1840_WS_TS_CNTL();
    SMOT1840_WS_SPE_OUT WS_SPE_OUT = new SMOT1840_WS_SPE_OUT();
    SMOT1840_WS_REC_OUT WS_REC_OUT = new SMOT1840_WS_REC_OUT();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRVCH BPRVCHD = new BPRVCH();
    SCCGWA SCCGWA;
    SMB1830_AWA_1830 SMB1830_AWA_1830;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS_TSQOUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1840 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1830_AWA_1830>");
        SMB1830_AWA_1830 = (SMB1830_AWA_1830) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS_TSQOUT() throws IOException,SQLException,Exception {
        B005_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        if (!IBS.isNumeric(SMB1830_AWA_1830.FLD_CNT+"")) {
            SMB1830_AWA_1830.FLD_CNT = 0;
        }
        BPRVCHD.DATA_TXT.FLD_CNT = SMB1830_AWA_1830.FLD_CNT;
        IBS.CPY2CLS(SCCGWA, SMB1830_AWA_1830.FLD_TXT, BPRVCHD.DATA_TXT.FLD_TXT);
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= SMB1830_AWA_1830.FLD_CNT 
            && WS_TEMP_VARIABLE.WS_I <= 150; WS_TEMP_VARIABLE.WS_I += 1) {
            B025_OUT_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B005_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = WS_TS_CNTL.WS_TS_MAIN_TIT;
        SCCMPAG.MAX_COL_NO = WS_TS_CNTL.WS_TS_MAX_RECLEN;
        SCCMPAG.SUBT_ROW_CNT = WS_TS_CNTL.WS_TS_TIT_NUM;
        SCCMPAG.SCR_ROW_CNT = 50;
        B_MPAG();
        if (pgmRtn) return;
        WS_SPE_OUT.WS_OUT_FUNC = SMB1830_AWA_1830.FUNC;
        WS_SPE_OUT.WS_OUT_PTYP = SMB1830_AWA_1830.PTYP;
        WS_SPE_OUT.WS_OUT_CODE = SMB1830_AWA_1830.CODE;
        WS_SPE_OUT.WS_OUT_EFFDATE = "" + SMB1830_AWA_1830.EFFDATE;
        JIBS_tmp_int = WS_SPE_OUT.WS_OUT_EFFDATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_SPE_OUT.WS_OUT_EFFDATE = "0" + WS_SPE_OUT.WS_OUT_EFFDATE;
        WS_SPE_OUT.WS_OUT_EXPDATE = "" + SMB1830_AWA_1830.EXPDATE;
        JIBS_tmp_int = WS_SPE_OUT.WS_OUT_EXPDATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_SPE_OUT.WS_OUT_EXPDATE = "0" + WS_SPE_OUT.WS_OUT_EXPDATE;
        WS_SPE_OUT.WS_OUT_DESC = SMB1830_AWA_1830.DESC;
        WS_SPE_OUT.WS_OUT_CDESC = SMB1830_AWA_1830.CDESC;
        WS_SPE_OUT.WS_OUT_LEN = "" + SMB1830_AWA_1830.LEN;
        JIBS_tmp_int = WS_SPE_OUT.WS_OUT_LEN.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) WS_SPE_OUT.WS_OUT_LEN = "0" + WS_SPE_OUT.WS_OUT_LEN;
        WS_SPE_OUT.WS_OUT_FLD_CNT = "" + SMB1830_AWA_1830.FLD_CNT;
        JIBS_tmp_int = WS_SPE_OUT.WS_OUT_FLD_CNT.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) WS_SPE_OUT.WS_OUT_FLD_CNT = "0" + WS_SPE_OUT.WS_OUT_FLD_CNT;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_SPE_OUT);
        WS_TEMP_VARIABLE.WS_LEN = 153;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void B025_OUT_RECORD() throws IOException,SQLException,Exception {
        WS_REC_OUT.WS_REC_NO = WS_TEMP_VARIABLE.WS_I;
        WS_REC_OUT.WS_REC_DATA = IBS.CLS2CPY(SCCGWA, BPRVCHD.DATA_TXT.FLD_TXT.FLD[WS_TEMP_VARIABLE.WS_I-1]);
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_REC_OUT);
        WS_TEMP_VARIABLE.WS_LEN = 200;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TSQ_REC;
        SCCMPAG.DATA_LEN = WS_TEMP_VARIABLE.WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
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
