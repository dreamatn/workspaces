package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCMPY {
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "CM640";
    String WS_ERR_MSG = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DCCF640 DCCF640 = new DCCF640();
    CICCUST CICCUST = new CICCUST();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCMPY DCCSCMPY;
    public void MP(SCCGWA SCCGWA, DCCSCMPY DCCSCMPY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCMPY = DCCSCMPY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCMPY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCF640);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_PSW_TRK_CHECK();
        if (pgmRtn) return;
        B030_READ_PROCESS();
        if (pgmRtn) return;
        B040_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCMPY.CARD_NO);
        CEP.TRC(SCCGWA, DCCSCMPY.TRK2_DAT);
        CEP.TRC(SCCGWA, DCCSCMPY.TRK3_DAT);
        CEP.TRC(SCCGWA, DCCSCMPY.CARD_PSW);
        if (DCCSCMPY.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCSCMPY.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.ADSC_TYPE);
        if (DCCUCINF.ADSC_TYPE != 'C') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_COMPANY_CARD);
        }
        CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CINO);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_IDTYP);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_IDNO);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CNM);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_TEL_NO);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
        CEP.TRC(SCCGWA, DCCUCINF.ISSU_DT);
        CEP.TRC(SCCGWA, DCCUCINF.ISSU_BR);
    }
    public void B020_PSW_TRK_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPCDCK);
        if (DCCSCMPY.CARD_PSW.trim().length() > 0) {
            DCCPCDCK.FUNC_CODE = 'P';
        }
        if (DCCSCMPY.TRK2_DAT.trim().length() > 0 
            || DCCSCMPY.TRK3_DAT.trim().length() > 0) {
            DCCPCDCK.FUNC_CODE = 'T';
        }
        if (DCCSCMPY.CARD_PSW.trim().length() > 0 
            && (DCCSCMPY.TRK2_DAT.trim().length() > 0 
            || DCCSCMPY.TRK3_DAT.trim().length() > 0)) {
            DCCPCDCK.FUNC_CODE = 'B';
        }
        if (DCCSCMPY.CARD_PSW.trim().length() == 0 
            && DCCSCMPY.TRK2_DAT.trim().length() == 0 
            && DCCSCMPY.TRK3_DAT.trim().length() == 0) {
            DCCPCDCK.FUNC_CODE = 'N';
        }
        DCCPCDCK.CARD_NO = DCCSCMPY.CARD_NO;
        DCCPCDCK.CARD_PSW = DCCSCMPY.CARD_PSW;
        DCCPCDCK.TRK2_DAT = DCCSCMPY.TRK2_DAT;
        DCCPCDCK.TRK3_DAT = DCCSCMPY.TRK3_DAT;
        S000_CALL_DCZPCDCK();
        if (pgmRtn) return;
    }
    public void B030_READ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = DCCSCMPY.CARD_NO;
        CICQACRL.DATA.AC_REL = "04";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_TEL_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        CEP.TRC(SCCGWA, DDRMST.OWNER_BR);
        CEP.TRC(SCCGWA, DDRMST.CROS_DR_FLG);
        CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
    }
    public void B040_DATA_OUTPUT() throws IOException,SQLException,Exception {
        DCCF640.AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        DCCF640.AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DCCF640.AC_STS = DDRMST.AC_STS;
        DCCF640.AC_STS_WROD = DDRMST.AC_STS_WORD;
        DCCF640.AC_OPEN_BR = DDRMST.OPEN_DP;
        DCCF640.AC_OWNER_BR = DDRMST.OWNER_BR;
        DCCF640.CROS_DR_FLG = DDRMST.CROS_DR_FLG;
        DCCF640.CROS_CR_FLG = DDRMST.CROS_CR_FLG;
        DCCF640.AVL_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
        DCCF640.CURR_BAL = DDRCCY.CURR_BAL;
        DCCF640.AC_TYPE = DDRMST.AC_TYPE;
        DCCF640.AC_CINO = CICQACRI.O_DATA.O_CI_NO;
        DCCF640.AC_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
        DCCF640.AC_IDNO = CICCUST.O_DATA.O_ID_NO;
        DCCF640.AC_CINM = CICCUST.O_DATA.O_CI_NM;
        DCCF640.AC_TEL_NO = CICCUST.O_DATA.O_TEL_NO;
        DCCF640.CARD_CINO = DCCUCINF.CARD_HLDR_CINO;
        DCCF640.CARD_IDTYP = DCCUCINF.CARD_HLDR_IDTYP;
        DCCF640.CARD_IDNO = DCCUCINF.CARD_HLDR_IDNO;
        DCCF640.CARD_CINM = DCCUCINF.CARD_HLDR_CNM;
        DCCF640.CARD_TEL_NO = DCCUCINF.CARD_TEL_NO;
        DCCF640.CARD_STS = DCCUCINF.CARD_STS;
        DCCF640.CARD_STS_WORD = DCCUCINF.CARD_STSW;
        DCCF640.CARD_OPEN_DATE = DCCUCINF.ISSU_DT;
        DCCF640.CARD_OPEN_BR = DCCUCINF.ISSU_BR;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = DCCF640;
        SCCFMT.DATA_LEN = 1224;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.eqWhere = "CUS_AC";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
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
