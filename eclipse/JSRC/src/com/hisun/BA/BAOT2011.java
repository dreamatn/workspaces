package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.GD.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2011 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BAP01";
    BAOT2011_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BAOT2011_WS_TEMP_VARIABLE();
    BAOT2011_WS_MAC WS_MAC = new BAOT2011_WS_MAC();
    BAOT2011_WS_OUT_DATA WS_OUT_DATA = new BAOT2011_WS_OUT_DATA();
    BARLOSS BARLOSS = new BARLOSS();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BACFLOSS BACFLOSS = new BACFLOSS();
    BACUBINF BACUBINF = new BACUBINF();
    BACUPRIT BACUPRIT = new BACUPRIT();
    BACUPROD BACUPROD = new BACUPROD();
    BACFMST1 BACFMST1 = new BACFMST1();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICACCU CICACCU = new CICACCU();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    SCCHMPW SCCHMPW = new SCCHMPW();
    BARFEDL BARFEDL = new BARFEDL();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    BACFFEDL BACFFEDL = new BACFFEDL();
    SCCGWA SCCGWA;
    BAB2011_AWA_2011 BAB2011_AWA_2011;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA, BAB2011_AWA_2011 BAB2011_AWA_2011) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BAB2011_AWA_2011 = BAB2011_AWA_2011;
        CEP.TRC(SCCGWA);
        A00_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BAOT2011 return!");
        Z_RET();
    }
    public void A00_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2011_AWA_2011>");
        BAB2011_AWA_2011 = (BAB2011_AWA_2011) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.CNTR_NO);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.S_SEQNO);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.ID_NO);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.XZK_FLG);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.LP_BR);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.LP_GY);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.VOU_TYPE);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.DY_BR);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.FEE_CUR);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.FEE_TYP);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.FEE_AMT);
        CEP.TRC(SCCGWA, BAB2011_AWA_2011.SXF_RMK);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_INPUT_ERR, WS_TEMP_VARIABLE.WS_MSGID);
        S000_ERR_MSG_PROC_LAST();
        B200_BAZUPRIT_PROC();
        B300_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BAB2011_AWA_2011.XZK_FLG != 'Y' 
            && BAB2011_AWA_2011.XZK_FLG != 'N') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_XZK_FLG_IS_WRONG, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BAB2011_AWA_2011.XZK_FLG == 'Y') {
            if (BAB2011_AWA_2011.LP_BR == 0 
                || BAB2011_AWA_2011.LP_GY.trim().length() == 0 
                || BAB2011_AWA_2011.VOU_TYPE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_Y_BR_GY_M_TYP_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BAB2011_AWA_2011.FEE_CUR.trim().length() == 0 
                || BAB2011_AWA_2011.FEE_TYP.trim().length() == 0 
                || BAB2011_AWA_2011.FEE_AMT == 0) {
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_FEE_INFOR_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BAB2011_AWA_2011.DY_BR;
        S000_CALL_BPZPQORG();
    }
    public void B200_BAZUPRIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUPRIT);
        BACUPRIT.COMM_DATA.CNTR_NO = BAB2011_AWA_2011.CNTR_NO;
        BACUPRIT.COMM_DATA.ACCT_CNT = BAB2011_AWA_2011.S_SEQNO;
        BACUPRIT.COMM_DATA.BILL_NO = BAB2011_AWA_2011.ID_NO;
        BACUPRIT.COMM_DATA.XZK_FLG = BAB2011_AWA_2011.XZK_FLG;
        BACUPRIT.COMM_DATA.LP_BR = BAB2011_AWA_2011.LP_BR;
        BACUPRIT.COMM_DATA.LP_GY = BAB2011_AWA_2011.LP_GY;
        BACUPRIT.COMM_DATA.VOU_TYPE = BAB2011_AWA_2011.VOU_TYPE;
        BACUPRIT.COMM_DATA.PRT_BR = BAB2011_AWA_2011.DY_BR;
        BACUPRIT.COMM_DATA.FEE_CUR = BAB2011_AWA_2011.FEE_CUR;
        BACUPRIT.COMM_DATA.FEE_TYP = BAB2011_AWA_2011.FEE_TYP;
        BACUPRIT.COMM_DATA.FEE_AMT = BAB2011_AWA_2011.FEE_AMT;
        BACUPRIT.COMM_DATA.SXF_RMK = BAB2011_AWA_2011.SXF_RMK;
        S000_CALL_BAZUPRIT();
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_OUT_ENCR = BACUPRIT.COMM_DATA.ENCR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 16;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BAZUPRIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-PRT-BILL", BACUPRIT);
        if (BACUPRIT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACUPRIT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
