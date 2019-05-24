package com.hisun.CM;

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
import com.hisun.SM.SMCMSG_ERROR_MSG;

public class CMOT9920 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPX03";
    String CPN_U_MAINTAIN_RSTA = "CM-U-MAINTAIN-RSTA";
    CMOT9920_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMOT9920_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    String WS_TSQ_NM = " ";
    CMCF920 CMCF920 = new CMCF920();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    CMCTLIBB CMCTLIBB = new CMCTLIBB();
    CMRTRSTA CMRTRSTA = new CMRTRSTA();
    SCCGWA SCCGWA;
    CMB9920_AWA_9920 CMB9920_AWA_9920;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMOT9920 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB9920_AWA_9920>");
        CMB9920_AWA_9920 = (CMB9920_AWA_9920) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        WS_TSQ_NM = SCCGWA.COMM_AREA.TERM_ID;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMB9920_AWA_9920.FILE_NAM);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B100_READ_CMTTRSTA();
        if (pgmRtn) return;
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CMB9920_AWA_9920.FILE_NAM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            if (CMB9920_AWA_9920.FILE_NAM.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(CMB9920_AWA_9920.FILE_NAM);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_READ_CMTTRSTA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCTLIBB);
        IBS.init(SCCGWA, CMRTRSTA);
        CMCTLIBB.FUNC = '3';
        CMCTLIBB.LEN = 173;
        CMCTLIBB.POINTER = CMRTRSTA;
        CMRTRSTA.KEY.FILE_NAME = CMB9920_AWA_9920.FILE_NAM;
        T000_CALL_CMZSRSTA();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCF920);
        IBS.init(SCCGWA, SCCFMT);
        CMCF920.FILE_NAME = CMRTRSTA.KEY.FILE_NAME;
        CMCF920.SYS_DATE = CMRTRSTA.SYS_DATE;
        CMCF920.BU_TYPE = CMRTRSTA.BU_TYPE;
        CMCF920.FILE_PROC_STS = CMRTRSTA.FILE_PROC_STS;
        CMCF920.MSG = CMRTRSTA.MSG;
        CEP.TRC(SCCGWA, CMRTRSTA.KEY.FILE_NAME);
        CEP.TRC(SCCGWA, CMRTRSTA.FILE_PROC_STS);
        CEP.TRC(SCCGWA, CMRTRSTA.TS);
        CEP.TRC(SCCGWA, CMRTRSTA.BU_TYPE);
        CEP.TRC(SCCGWA, CMRTRSTA.SCENE_FLG);
        CEP.TRC(SCCGWA, CMRTRSTA.MSG);
        CEP.TRC(SCCGWA, "OUT PUT!!!");
        SCCFMT.FMTID = "CM510";
