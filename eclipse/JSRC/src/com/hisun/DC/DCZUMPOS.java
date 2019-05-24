package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUMPOS {
    DBParm DCTPOSRD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRPOSRD DCRPOSRD = new DCRPOSRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUMPOS DCCUMPOS;
    public void MP(SCCGWA SCCGWA, DCCUMPOS DCCUMPOS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUMPOS = DCCUMPOS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUMPOS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCUMPOS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DCCUMPOS.FUNC_CODE == 'Q') {
            B020_INQ_POS_DATA();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCUMPOS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B030_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUMPOS.DATA.AC_DT);
        CEP.TRC(SCCGWA, DCCUMPOS.DATA.JRNNO);
        if (DCCUMPOS.DATA.AC_DT == 0 
            || DCCUMPOS.DATA.JRNNO == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_OLD_JRNNO_MUST_INPUT, DCCUMPOS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_POS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPOSRD);
        DCRPOSRD.KEY.AC_DT = DCCUMPOS.DATA.AC_DT;
        DCRPOSRD.KEY.JRNNO = DCCUMPOS.DATA.JRNNO;
        T000_READ_DCTPOSRD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CONSUME_NOT_EXIST, DCCUMPOS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        DCCUMPOS.DATA.AC_NO = DCRPOSRD.AC_NO;
        DCCUMPOS.DATA.CARD_NO = DCRPOSRD.CARD_NO;
        DCCUMPOS.DATA.TR_CD = DCRPOSRD.TR_CD;
        DCCUMPOS.DATA.TR_FLG = DCRPOSRD.TR_FLG;
        DCCUMPOS.DATA.TR_DT = DCRPOSRD.TR_DT;
        DCCUMPOS.DATA.TR_TM = DCRPOSRD.TR_TM;
        DCCUMPOS.DATA.TR_EFF_DT = DCRPOSRD.TR_EFF_DT;
        DCCUMPOS.DATA.CANCEL_DT = DCRPOSRD.CANCEL_DT;
        DCCUMPOS.DATA.TR_AMT = DCRPOSRD.TR_AMT;
        DCCUMPOS.DATA.AMT_FILLER = 0X01;
        DCCUMPOS.DATA.TR_CCY = DCRPOSRD.TR_CCY;
        DCCUMPOS.DATA.TR_BR = DCRPOSRD.TR_BR;
        DCCUMPOS.DATA.TR_TLR = DCRPOSRD.TR_TLR;
        DCCUMPOS.DATA.LNK_FLG = DCRPOSRD.LNK_FLG;
        DCCUMPOS.DATA.LNK_AC_DT = DCRPOSRD.LNK_AC_DT;
        DCCUMPOS.DATA.LNK_TR_CD = DCRPOSRD.LNK_TR_CD;
        DCCUMPOS.DATA.LNK_JRNNO = DCRPOSRD.LNK_JRNNO;
        SCCFMT.FMTID = "DC311";
        CEP.TRC(SCCGWA, DCCUMPOS.DATA);
        SCCFMT.DATA_PTR = DCCUMPOS.DATA;
        SCCFMT.DATA_LEN = 171;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTPOSRD() throws IOException,SQLException,Exception {
        DCTPOSRD_RD = new DBParm();
        DCTPOSRD_RD.TableName = "DCTPOSRD";
        DCTPOSRD_RD.col = "AC_DT, JRNNO, AC_NO, CARD_NO, TR_CD, TR_FLG, TR_DT, TR_TM, TR_EFF_DT, CANCEL_DT, TR_AMT, TR_CCY, TR_BR, TR_TLR, LNK_FLG, LNK_AC_DT, LNK_TR_CD, LNK_JRNNO, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRPOSRD, DCTPOSRD_RD);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUMPOS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUMPOS=");
            CEP.TRC(SCCGWA, DCCUMPOS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
