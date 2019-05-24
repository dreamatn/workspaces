package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5380 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_BP_FAV = "BP-FAV";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 15;
    int K_COL_CNT = 3;
    String WS_ERR_MSG = " ";
    BPOT5380_WS_OUT_DATE WS_OUT_DATE = new BPOT5380_WS_OUT_DATE();
    BPOT5380_WS_MPAG_DATA WS_MPAG_DATA = new BPOT5380_WS_MPAG_DATA();
    BPCEX BPCEX = new BPCEX();
    BPCTENOR BPCTENOR = new BPCTENOR();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRFAVB BPCRFAVB = new BPCRFAVB();
    BPREXFAV BPREXFAV = new BPREXFAV();
    BPCPFAV BPCPFAV = new BPCPFAV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5380_AWA_5380 BPB5380_AWA_5380;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT5380 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5380_AWA_5380>");
        BPB5380_AWA_5380 = (BPB5380_AWA_5380) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_OUT_DATE.WS_ERR_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXFAV);
        IBS.init(SCCGWA, BPCRFAVB);
        BPREXFAV.KEY.FAV_CODE = BPB5380_AWA_5380.FAV_CODE;
        BPREXFAV.KEY.CCY = BPB5380_AWA_5380.CCY;
        if (BPB5380_AWA_5380.FEE_TYPE == null) BPB5380_AWA_5380.FEE_TYPE = "";
        JIBS_tmp_int = BPB5380_AWA_5380.FEE_TYPE.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPB5380_AWA_5380.FEE_TYPE += " ";
        BPREXFAV.FAV_TYPE = BPB5380_AWA_5380.FEE_TYPE.substring(0, 1);
        BPREXFAV.CAL_MTH = BPB5380_AWA_5380.CMP_FLG;
        BPREXFAV.EFF_DATE = BPB5380_AWA_5380.EFF_DT;
        BPREXFAV.EXP_DATE = BPB5380_AWA_5380.EXP_DT;
        CEP.TRC(SCCGWA, BPREXFAV.KEY.FAV_CODE);
        CEP.TRC(SCCGWA, BPREXFAV.KEY.CCY);
        CEP.TRC(SCCGWA, BPREXFAV.FAV_TYPE);
        CEP.TRC(SCCGWA, BPREXFAV.CAL_MTH);
        CEP.TRC(SCCGWA, BPREXFAV.EFF_DATE);
        CEP.TRC(SCCGWA, BPREXFAV.EXP_DATE);
        if (BPREXFAV.KEY.FAV_CODE.trim().length() == 0) {
            BPREXFAV.KEY.FAV_CODE = "%%%%%";
        }
        if (BPREXFAV.KEY.CCY.trim().length() == 0) {
            BPREXFAV.KEY.CCY = "%%%";
        }
        if (BPREXFAV.FAV_TYPE.trim().length() == 0) {
            BPREXFAV.FAV_TYPE = "%%";
        }
        BPCRFAVB.INFO.FUNC = 'S';
        S000_CALL_BPZTFAVB();
        if (pgmRtn) return;
        B000_01_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B000_01_OUTPUT_PROC() throws IOException,SQLException,Exception {
        R000_READNEXT_PROC();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_QUEUE_TITLE();
        if (pgmRtn) return;
        while (BPCRFAVB.INFO.RTN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, "RUN HERE");
            R000_DATA_OUTPUT_QUEUE_DETAIL();
            if (pgmRtn) return;
            R000_READNEXT_PROC();
            if (pgmRtn) return;
        }
        R000_ENDBR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "RUN FIN");
    }
    public void R000_ENDBR() throws IOException,SQLException,Exception {
        BPCRFAVB.INFO.FUNC = 'E';
        BPCRFAVB.INFO.POINTER = BPREXFAV;
        IBS.CALLCPN(SCCGWA, "BP-FAV", BPCRFAVB);
        if (BPCRFAVB.INFO.RTN_INFO == 'Y') {
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFAVB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "ENDBR OK");
    }
    public void R000_DATA_OUTPUT_QUEUE_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_MPAG_DATA);
        WS_MPAG_DATA.WS_Q_FEE_CODE = BPREXFAV.KEY.FAV_CODE;
        WS_MPAG_DATA.WS_Q_CCY = BPREXFAV.KEY.CCY;
        WS_MPAG_DATA.WS_Q_FEE_TYPE = BPREXFAV.FAV_TYPE;
        WS_MPAG_DATA.WS_Q_CAL_FLG = BPREXFAV.CAL_MTH;
        WS_MPAG_DATA.WS_Q_CDESC = BPREXFAV.FAV_CDESC;
        WS_MPAG_DATA.WS_Q_DESC = BPREXFAV.FAV_EDESC;
        WS_MPAG_DATA.WS_Q_EFF_DT = BPREXFAV.EFF_DATE;
        WS_MPAG_DATA.WS_Q_EXP_DT = BPREXFAV.EXP_DATE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_MPAG_DATA);
        SCCMPAG.DATA_LEN = 108;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_DATA_OUTPUT_QUEUE_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 108;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_PROC() throws IOException,SQLException,Exception {
        BPCRFAVB.INFO.FUNC = 'R';
        BPCRFAVB.INFO.LEN = 2915;
        BPCRFAVB.INFO.POINTER = BPREXFAV;
        IBS.CALLCPN(SCCGWA, "BP-FAV", BPCRFAVB);
        if (BPCRFAVB.INFO.RTN_INFO == 'Y'
            || BPCRFAVB.INFO.RTN_INFO == 'N') {
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFAVB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPREXFAV.KEY.FAV_CODE);
        CEP.TRC(SCCGWA, BPREXFAV.FAV_TYPE);
        CEP.TRC(SCCGWA, BPREXFAV.KEY.CCY);
        CEP.TRC(SCCGWA, BPREXFAV.EFF_DATE);
    }
    public void S000_CALL_BPZTFAVB() throws IOException,SQLException,Exception {
        BPCRFAVB.INFO.LEN = 2915;
        BPCRFAVB.INFO.POINTER = BPREXFAV;
        CEP.TRC(SCCGWA, "LINK");
        IBS.CALLCPN(SCCGWA, K_BP_FAV, BPCRFAVB);
        CEP.TRC(SCCGWA, "BPZRFAVB RETURN");
        CEP.TRC(SCCGWA, BPCRFAVB.RC.RC_CODE);
        if (BPCRFAVB.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCRFAVB.RC);
        }
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
